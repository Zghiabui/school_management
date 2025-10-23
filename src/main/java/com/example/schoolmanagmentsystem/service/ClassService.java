package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.ClassDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.exception.RoomTimeConflictException; // <-- tạo theo hướng dẫn trước
import com.example.schoolmanagmentsystem.repository.ClassRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    /* ==================== CREATE ==================== */

    public ClassDTO createClass(ClassDTO classDTO) {
        validate(classDTO, null); // ⬅️ kiểm tra chồng lấn trước khi lưu
        Class entity = convertToEntity(classDTO);
        Class saved = classRepository.save(entity);
        return convertToDTO(saved);
    }

    /* ==================== READ ==================== */

    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClassDTO getClassById(Long id) {
        Class entity = classRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));
        return convertToDTO(entity);
    }

    /* ==================== UPDATE ==================== */

    public ClassDTO updateClass(Long id, ClassDTO classDTO) {
        // validate trước (excludeId = id để bỏ qua chính nó)
        validate(classDTO, id);

        Class existing = classRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Class not found"));

        // cập nhật thuộc tính cơ bản
        existing.setSemester(classDTO.getSemester());
        existing.setAcademicYear(classDTO.getAcademicYear());
        existing.setRoom(classDTO.getRoom());
        existing.setSchedule(classDTO.getSchedule());
        existing.setTeacherId(classDTO.getTeacherId());

        // 3 field mới để kiểm tra trùng giờ
        existing.setDayOfWeek(classDTO.getDayOfWeek());
        existing.setStartTime(classDTO.getStartTime());
        existing.setEndTime(classDTO.getEndTime());

        if (classDTO.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(classDTO.getSubjectId())
                    .orElseThrow(() -> new NoSuchElementException("Subject not found"));
            existing.setSubject(subject);
        } else {
            existing.setSubject(null);
        }

        Class updated = classRepository.save(existing);
        return convertToDTO(updated);
    }

    /* ==================== DELETE ==================== */

    public void deleteClass(Long id) {
        if (!classRepository.existsById(id)) {
            throw new NoSuchElementException("Class not found");
        }
        classRepository.deleteById(id);
    }

    /* ==================== VALIDATION ==================== */

    private void validate(ClassDTO dto, Long excludeId) {
        // 1) Kiểm tra input bắt buộc
        if (dto.getDayOfWeek() == null)
            throw new IllegalArgumentException("Thiếu 'dayOfWeek' (1..7)");
        if (dto.getStartTime() == null || dto.getEndTime() == null)
            throw new IllegalArgumentException("Thiếu 'startTime' hoặc 'endTime'");
        if (dto.getRoom() == null || dto.getRoom().isBlank())
            throw new IllegalArgumentException("Thiếu 'room'");
        if (dto.getAcademicYear() == null || dto.getAcademicYear().isBlank())
            throw new IllegalArgumentException("Thiếu 'academicYear'");
        if (dto.getSemester() == null || dto.getSemester().isBlank())
            throw new IllegalArgumentException("Thiếu 'semester'");

        LocalTime start = dto.getStartTime();
        LocalTime end   = dto.getEndTime();

        // 2) start < end
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Giờ bắt đầu phải trước giờ kết thúc");
        }

        // 3) Kiểm tra chồng lấn (overlap) trong cùng phòng/ngày/năm/kỳ
        boolean overlapped = classRepository.existsOverlap(
                dto.getRoom(),
                dto.getDayOfWeek(),
                dto.getAcademicYear(),
                dto.getSemester(),
                start, end,
                excludeId // null khi create, id khi update
        );

        if (overlapped) {
            // dùng RuntimeException nếu bạn chưa tạo RoomTimeConflictException
            throw new RoomTimeConflictException(
                    "Phòng %s đã có lớp trong khoảng %s–%s (ngày %d, %s, %s)"
                            .formatted(dto.getRoom(), start, end,
                                    dto.getDayOfWeek(), dto.getSemester(), dto.getAcademicYear()));
        }
    }

    /* ==================== MAPPING ==================== */

    private Class convertToEntity(ClassDTO dto) {
        Class entity = new Class();
        entity.setSemester(dto.getSemester());
        entity.setAcademicYear(dto.getAcademicYear());
        entity.setRoom(dto.getRoom());
        entity.setSchedule(dto.getSchedule());
        entity.setTeacherId(dto.getTeacherId());

        // 3 field mới
        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new NoSuchElementException("Subject not found"));
            entity.setSubject(subject);
        }
        return entity;
    }

    private ClassDTO convertToDTO(Class entity) {
        ClassDTO dto = new ClassDTO();
        dto.setClassId(entity.getClassId());
        dto.setSemester(entity.getSemester());
        dto.setAcademicYear(entity.getAcademicYear());
        dto.setRoom(entity.getRoom());
        dto.setSchedule(entity.getSchedule());
        dto.setTeacherId(entity.getTeacherId());

        // 3 field mới
        dto.setDayOfWeek(entity.getDayOfWeek());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());

        if (entity.getSubject() != null) {
            dto.setSubjectId(entity.getSubject().getSubjectId());
            dto.setSubjectName(entity.getSubject().getSubjectName());
        }
        return dto;
    }
}
