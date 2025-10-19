package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.GradeDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Grade;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.repository.ClassRepository;
import com.example.schoolmanagmentsystem.repository.GradeRepository;
import com.example.schoolmanagmentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    public GradeDTO createGrade(GradeDTO gradeDTO) {
        Grade grade = convertToEntity(gradeDTO);
        Grade savedGrade = gradeRepository.save(grade);
        return convertToDTO(savedGrade);
    }

    public List<GradeDTO> getAllGrades() {
        return gradeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public GradeDTO getGradeById(Long id) {
        Grade grade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));
        return convertToDTO(grade);
    }

    public GradeDTO updateGrade(Long id, GradeDTO gradeDTO) {
        Grade existingGrade = gradeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grade not found"));

        existingGrade.setAttendanceScore(gradeDTO.getAttendanceScore());
        existingGrade.setMidtermScore(gradeDTO.getMidtermScore());
        existingGrade.setFinalScore(gradeDTO.getFinalScore());

        if (gradeDTO.getStudentCode() != null) {
            Student student = studentRepository.findAll().stream().filter(s -> s.getStudentCode().equals(gradeDTO.getStudentCode())).findFirst()
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            existingGrade.setStudent(student);
        }

        if (gradeDTO.getClassId() != null) {
            Class aClass = classRepository.findById(gradeDTO.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            existingGrade.setAClass(aClass);
        }

        Grade updatedGrade = gradeRepository.save(existingGrade);
        return convertToDTO(updatedGrade);
    }

    public void deleteGrade(Long id) {
        gradeRepository.deleteById(id);
    }

    private Grade convertToEntity(GradeDTO dto) {
        Grade grade = new Grade();
        grade.setAttendanceScore(dto.getAttendanceScore());
        grade.setMidtermScore(dto.getMidtermScore());
        grade.setFinalScore(dto.getFinalScore());

        if (dto.getStudentCode() != null) {
            Student student = studentRepository.findAll().stream().filter(s -> s.getStudentCode().equals(dto.getStudentCode())).findFirst()
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            grade.setStudent(student);
        }

        if (dto.getClassId() != null) {
            Class aClass = classRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            grade.setAClass(aClass);
        }

        return grade;
    }

    private GradeDTO convertToDTO(Grade grade) {
        GradeDTO dto = new GradeDTO();
        dto.setGradeId(grade.getGradeId());
        dto.setAttendanceScore(grade.getAttendanceScore());
        dto.setMidtermScore(grade.getMidtermScore());
        dto.setFinalScore(grade.getFinalScore());

        if (grade.getStudent() != null) {
            dto.setStudentCode(grade.getStudent().getStudentCode());
        }

        if (grade.getAClass() != null) {
            dto.setClassId(grade.getAClass().getClassId());
        }

        return dto;
    }
}