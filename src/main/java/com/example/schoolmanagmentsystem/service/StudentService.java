package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.StudentDTO;
import com.example.schoolmanagmentsystem.entity.Class;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.exception.DuplicateDataException;
import com.example.schoolmanagmentsystem.repository.ClassRepository;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import com.example.schoolmanagmentsystem.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private ClassRepository classRepository;

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByStudentCode(studentDTO.getStudentCode())) {
            throw new DuplicateDataException("Mã số sinh viên '" + studentDTO.getStudentCode() + "' đã tồn tại!");
        }
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new DuplicateDataException("Email '" + studentDTO.getEmail() + "' đã tồn tại!");
        }
        if (studentRepository.existsByPhone(studentDTO.getPhone())) {
            throw new DuplicateDataException("SĐT '" + studentDTO.getPhone() + "' đã tồn tại!");
        }
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
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với ID: " + id));
        return convertToDTO(student);
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với ID: " + id));
        if (!Objects.equals(existingStudent.getStudentCode(), studentDTO.getStudentCode()) &&
                studentRepository.existsByStudentCodeAndStudentIdNot(studentDTO.getStudentCode(), id)) {
            throw new DuplicateDataException("Mã số sinh viên '" + studentDTO.getStudentCode() + "' đã tồn tại!");
        }
        if (!Objects.equals(existingStudent.getEmail(), studentDTO.getEmail()) &&
                studentRepository.existsByEmailAndStudentIdNot(studentDTO.getEmail(), id)) {
            throw new DuplicateDataException("Email '" + studentDTO.getEmail() + "' đã tồn tại!");
        }
        if (!Objects.equals(existingStudent.getPhone(), studentDTO.getPhone()) &&
                studentRepository.existsByPhoneAndStudentIdNot(studentDTO.getPhone(), id)) {
            throw new DuplicateDataException("SĐT '" + studentDTO.getPhone() + "' đã tồn tại!");
        }

        // Cập nhật các trường
        existingStudent.setStudentCode(studentDTO.getStudentCode());
        existingStudent.setName(studentDTO.getName());
        existingStudent.setDateOfBirth(studentDTO.getDateOfBirth());
        existingStudent.setPhone(studentDTO.getPhone());
        existingStudent.setEmail(studentDTO.getEmail());

        if (studentDTO.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(studentDTO.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khoa với ID: " + studentDTO.getFacultyId()));
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

    @Transactional
    public StudentDTO deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với ID: " + id));

        studentRepository.delete(student);
        return convertToDTO(student);
    }

    public List<StudentDTO> getStudentsByClassId(Long classId) {
        return studentRepository.findByClazz_ClassId(classId)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setStudentCode(dto.getStudentCode());
        student.setName(dto.getName());
        student.setDateOfBirth(dto.getDateOfBirth());
        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khoa với ID: " + dto.getFacultyId()));
            student.setFaculty(faculty);
        }else {
            throw new IllegalArgumentException("Faculty ID không được null");
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
            dto.setClassName(student.getClazz().getSubject().getSubjectName());
        }
        dto.setPhone(student.getPhone());
        dto.setEmail(student.getEmail());
        return dto;
    }
}

