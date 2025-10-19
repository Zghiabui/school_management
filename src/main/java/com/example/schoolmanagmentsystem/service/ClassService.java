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
        Class classEntity = convertToEntity(classDTO);
        Class savedClass = classRepository.save(classEntity);
        return convertToDTO(savedClass);
    }

    public List<ClassDTO> getAllClasses() {
        return classRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ClassDTO getClassById(Long id) {
        Class classEntity = classRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));
        return convertToDTO(classEntity);
    }

    private Class convertToEntity(ClassDTO dto) {
        Class classEntity = new Class();
        classEntity.setSemester(dto.getSemester());
        classEntity.setAcademicYear(dto.getAcademicYear());
        classEntity.setRoom(dto.getRoom());
        classEntity.setSchedule(dto.getSchedule());

        if (dto.getSubjectId() != null) {
            Subject subject = subjectRepository.findById(dto.getSubjectId())
                    .orElseThrow(() -> new RuntimeException("Subject not found"));
            classEntity.setSubject(subject);
        }
        classEntity.setTeacherId(dto.getTeacherId());

        return classEntity;
    }

    private ClassDTO convertToDTO(Class classEntity) {
        ClassDTO dto = new ClassDTO();
        dto.setClassId(classEntity.getClassId());
        dto.setSemester(classEntity.getSemester());
        dto.setAcademicYear(classEntity.getAcademicYear());
        dto.setRoom(classEntity.getRoom());
        dto.setSchedule(classEntity.getSchedule());

        if (classEntity.getSubject() != null) {
            dto.setSubjectId(classEntity.getSubject().getSubjectId());
            dto.setSubjectName(classEntity.getSubject().getSubjectName());
        }
        dto.setTeacherId(classEntity.getTeacherId());

        return dto;
    }
}
