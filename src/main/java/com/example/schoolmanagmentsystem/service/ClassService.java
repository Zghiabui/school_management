package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.ClassDTO;
import com.example.schoolmanagmentsystem.dto.StudentDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.exception.RoomTimeConflictException;
import com.example.schoolmanagmentsystem.repository.ClassRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentService studentService;

    /* ==================== CREATE ==================== */
    @Transactional
    public ClassDTO createClass(ClassDTO classDTO) {
        validate(classDTO, null); // kiểm tra chồng lấn trước khi lưu
        Class entity = convertToEntity(classDTO);

        // gắn Subject nếu có
        if (classDTO.getSubjectId() != null) {
            Subject subject = findSubjectById(classDTO.getSubjectId());
            entity.setSubject(subject);
        }

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
        Class entity = findClassById(id);
        return convertToDTO(entity);
    }

    public List<StudentDTO> getStudentsByClassId(Long classId) {
        findClassById(classId); // Check if class exists
        return studentService.getStudentsByClassId(classId);
    }

    private Class findClassById(Long id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy lớp với ID: " + id));
    }

    private Subject findSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy môn học với ID: " + id));
    }

    /* ==================== UPDATE ==================== */
    @Transactional
    public ClassDTO updateClass(Long id, ClassDTO classDTO) {
        // validate trước (excludeId = id để bỏ qua chính nó)
        validate(classDTO, id);

        Class existing = findClassById(id);

        // cập nhật thuộc tính cơ bản
        existing.setSemester(classDTO.getSemester());
        existing.setAcademicYear(classDTO.getAcademicYear());
        existing.setRoom(classDTO.getRoom());
        existing.setSchedule(classDTO.getSchedule());
        existing.setTeacherId(classDTO.getTeacherId());

        // các field kiểm tra giờ
        existing.setDayOfWeek(classDTO.getDayOfWeek());
        existing.setStartTime(classDTO.getStartTime());
        existing.setEndTime(classDTO.getEndTime());

        if (classDTO.getSubjectId() != null) {
            Subject subject = findSubjectById(classDTO.getSubjectId());
            existing.setSubject(subject);
        } else {
            existing.setSubject(null);
        }

        Class updated = classRepository.save(existing);
        return convertToDTO(updated);
    }

    /* ==================== DELETE ==================== */
    @Transactional
    public ClassDTO deleteClass(Long id) {
        Class entity = findClassById(id);
        classRepository.delete(entity);
        return convertToDTO(entity);
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

        // 3) Kiểm tra chồng lấn (overlap) cùng phòng/ngày/năm/kỳ
        boolean overlapped = classRepository.existsOverlap(
                dto.getRoom(),
                dto.getDayOfWeek(),
                dto.getAcademicYear(),
                dto.getSemester(),
                start, end,
                excludeId // null khi create, id khi update
        );

        if (overlapped) {
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

        entity.setDayOfWeek(dto.getDayOfWeek());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());

        // Subject được gắn ở create/update qua findSubjectById()
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
