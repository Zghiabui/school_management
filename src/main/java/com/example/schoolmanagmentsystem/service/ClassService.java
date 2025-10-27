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

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired private ClassRepository classRepository;
    @Autowired private SubjectRepository subjectRepository;
    @Autowired private StudentService studentService;

    /* ==================== CREATE ==================== */
    @Transactional
    public ClassDTO createClass(ClassDTO classDTO) {
        validate(classDTO, null);
        Class entity = convertToEntity(classDTO);

        if (classDTO.getSubjectId() != null) {
            entity.setSubject(findSubjectById(classDTO.getSubjectId()));
        }

        return convertToDTO(classRepository.save(entity));
    }

    /* ==================== READ ==================== */
    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ClassDTO getClassById(Long id) {
        return convertToDTO(findClassById(id));
    }

    public List<StudentDTO> getStudentsByClassId(Long classId) {
        findClassById(classId);
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
        validate(classDTO, id);

        Class existing = findClassById(id);
        existing.setSemester(classDTO.getSemester());
        existing.setAcademicYear(classDTO.getAcademicYear());
        existing.setRoom(classDTO.getRoom());
        existing.setSchedule(classDTO.getSchedule());
        existing.setTeacherId(classDTO.getTeacherId());

        existing.setStudyDate(classDTO.getStudyDate());
        existing.setStartTime(classDTO.getStartTime());
        existing.setEndTime(classDTO.getEndTime());

        if (classDTO.getSubjectId() != null) {
            existing.setSubject(findSubjectById(classDTO.getSubjectId()));
        } else {
            existing.setSubject(null);
        }

        return convertToDTO(classRepository.save(existing));
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
        if (dto.getStudyDate() == null)
            throw new IllegalArgumentException("Thiếu 'studyDate'");
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
        if (!start.isBefore(end)) {
            throw new IllegalArgumentException("Giờ bắt đầu phải trước giờ kết thúc");
        }

        boolean overlapped = classRepository.existsOverlapByDate(
                dto.getRoom(),
                dto.getStudyDate(),
                dto.getAcademicYear(),
                dto.getSemester(),
                start, end,
                excludeId
        );

        if (overlapped) {
            throw new RoomTimeConflictException(
                    "Phòng %s đã có lớp trong khoảng %s–%s (ngày %s, %s, %s)"
                            .formatted(dto.getRoom(), start, end, dto.getStudyDate(),
                                    dto.getSemester(), dto.getAcademicYear()));
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

        entity.setStudyDate(dto.getStudyDate());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
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

        dto.setStudyDate(entity.getStudyDate());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());

        if (entity.getSubject() != null) {
            dto.setSubjectId(entity.getSubject().getSubjectId());
            dto.setSubjectName(entity.getSubject().getSubjectName());
        }
        return dto;
    }
}
