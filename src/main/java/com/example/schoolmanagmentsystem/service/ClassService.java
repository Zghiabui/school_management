package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.ClassDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.repository.ClassRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public ClassDTO createClass(ClassDTO classDTO) {
        Class entity = convertToEntity(classDTO);
        Class saved = classRepository.save(entity);
        return convertToDTO(saved);
    }

    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClassDTO getClassById(Long id) {
        Class entity = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        return convertToDTO(entity);
    }

    public ClassDTO updateClass(Long id, ClassDTO classDTO) {
        Class existing = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        // cập nhật thuộc tính cơ bản
        existing.setSemester(classDTO.getSemester());
        existing.setAcademicYear(classDTO.getAcademicYear());
        existing.setRoom(classDTO.getRoom());
        existing.setSchedule(classDTO.getSchedule());
        existing.setTeacherId(classDTO.getTeacherId());

        if (classDTO.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(classDTO.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            existing.setSubject(subject);
        } else {
            existing.setSubject(null);
        }

        Class updated = classRepository.save(existing);
        return convertToDTO(updated);
    }


    private Class convertToEntity(ClassDTO dto) {
        Class entity = new Class();
        entity.setSemester(dto.getSemester());
        entity.setAcademicYear(dto.getAcademicYear());
        entity.setRoom(dto.getRoom());
        entity.setSchedule(dto.getSchedule());
        entity.setTeacherId(dto.getTeacherId());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
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

        if (entity.getSubject() != null) {
            dto.setSubjectId(entity.getSubject().getSubjectId());
            dto.setSubjectName(entity.getSubject().getSubjectName()); // tiện cho FE hiển thị
        }
        return dto;
    }
}
