package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.StudentDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import com.example.schoolmanagmentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public StudentDTO createStudent(StudentDTO studentDTO) {
        Student student = convertToEntity(studentDTO);
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }

    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return convertToDTO(student);
    }



    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setStudentCode(dto.getStudentCode());
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            student.setFaculty(faculty);
        }
        student.setPhone(dto.getPhone());
        student.setEmail(dto.getEmail());
        student.setGradeId(dto.getGradeId());
        return student;
    }

    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setStudentId(student.getStudentId());
        dto.setStudentCode(student.getStudentCode());
        dto.setName(student.getName());
        dto.setDateOfBirth(student.getDateOfBirth());
        if (student.getFaculty() != null) {
            dto.setFacultyId(student.getFaculty().getFacultyId());
            dto.setFacultyName(student.getFaculty().getFacultyName());
        }
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        dto.setGradeId(student.getGradeId());
        return dto;
    }
}
