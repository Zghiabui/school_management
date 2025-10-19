package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.SubjectDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = convertToEntity(subjectDTO);
        Subject savedSubject = subjectRepository.save(subject);
        return convertToDTO(savedSubject);
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy môn học"));
        return convertToDTO(subject);
    }

    private Subject convertToEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setSubjectName(dto.getSubjectName());
        subject.setCredits(dto.getCredits());
        subject.setDescription(dto.getDescription());

        Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                .orElseThrow(() -> new IllegalArgumentException("Khoa không tồn tại với ID: " + dto.getFacultyId()));
        subject.setFaculty(faculty);

        return subject;
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setSubjectId(subject.getSubjectId());
        dto.setSubjectName(subject.getSubjectName());
        dto.setCredits(subject.getCredits());
        dto.setDescription(subject.getDescription());

        dto.setFacultyId(subject.getFaculty().getFacultyId());
        dto.setFacultyName(subject.getFaculty().getFacultyName());

        return dto;
    }
}
