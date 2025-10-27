package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.TeacherDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.entity.Teacher;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import com.example.schoolmanagmentsystem.repository.TeacherRepository;
import com.example.schoolmanagmentsystem.specification.TeacherSpecification;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {
        Teacher teacher = convertToEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return convertToDTO(savedTeacher);
    }

    public List<TeacherDTO> getAllTeachers() {
        return teacherRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TeacherDTO getTeacherById(Long id) {
        Teacher teacher = findTeacherById(id);
        if (teacher == null) {
            return null;
        }
        return convertToDTO(teacher);
    }

    public TeacherDTO updateTeacher(Long id, TeacherDTO teacherDTO) {
        Teacher teacher = findTeacherById(id);
        if (teacher == null) {
            return null;
        }
        
        teacher.setName(teacherDTO.getName());
        teacher.setAcademicRank(teacherDTO.getAcademicRank());
        teacher.setPhoneNumber(teacherDTO.getPhoneNumber());
        teacher.setEmail(teacherDTO.getEmail());
        teacher.setExperience(teacherDTO.getExperience());

        if (teacherDTO.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(teacherDTO.getFacultyId()).orElse(null);
            teacher.setFaculty(faculty);
        } else {
            teacher.setFaculty(null);
        }

        Teacher updatedTeacher = teacherRepository.save(teacher);
        return convertToDTO(updatedTeacher);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }

    public List<TeacherDTO> searchTeachers(String name, String email, Long facultyId) {
        Specification<Teacher> spec = TeacherSpecification.search(name, email, facultyId);
        return teacherRepository.findAll(spec).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<TeacherDTO> getTeachersBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new EntityNotFoundException("Subject not found with id: " + subjectId));
        Faculty faculty = subject.getFaculty();
        if (faculty == null) {
            throw new EntityNotFoundException("Faculty not found for the given subject");
        }
        List<Teacher> teachers = teacherRepository.findByFaculty(faculty);
        return teachers.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    private Teacher convertToEntity(TeacherDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setAcademicRank(dto.getAcademicRank());
        teacher.setPhoneNumber(dto.getPhoneNumber());
        teacher.setEmail(dto.getEmail());
        teacher.setExperience(dto.getExperience());
        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId()).orElse(null);
            teacher.setFaculty(faculty);
        }
        return teacher;
    }

    private TeacherDTO convertToDTO(Teacher teacher) {
        TeacherDTO dto = new TeacherDTO();
        dto.setTeacherId(teacher.getTeacherId());
        dto.setName(teacher.getName());
        dto.setAcademicRank(teacher.getAcademicRank());
        dto.setPhoneNumber(teacher.getPhoneNumber());
        dto.setEmail(teacher.getEmail());
        dto.setExperience(teacher.getExperience());
        if (teacher.getFaculty() != null) {
            dto.setFacultyId(teacher.getFaculty().getFacultyId());
        }
        return dto;
    }
}
