package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.StudentDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.repository.ClassRepository;
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

    @Autowired
    private ClassRepository classRepository;

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

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Cập nhật các trường
        existingStudent.setStudentCode(studentDTO.getStudentCode());
        existingStudent.setName(studentDTO.getName());
        existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        existingStudent.setPhone(studentDTO.getPhone());
        existingStudent.setEmail(studentDTO.getEmail());

        if (studentDTO.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            existingStudent.setFaculty(faculty);
        }

        if (studentDTO.getClassId() != null) {
            Class clazz = classRepository.findById(studentDTO.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            existingStudent.setClazz(clazz);
        }

        Student updatedStudent = studentRepository.save(existingStudent);
        return convertToDTO(updatedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
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
        if (dto.getClassId() != null) {
            Class clazz = classRepository.findById(dto.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            student.setClazz(clazz);
        }
        student.setPhone(dto.getPhone());
        student.setEmail(dto.getEmail());
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
        if (student.getClazz() != null) {
            dto.setClassId(student.getClazz().getClassId());
            dto.setClassName(student.getClazz().getSubject().getSubjectName()); // Corrected field name
        }
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        return dto;
    }
}

