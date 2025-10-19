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
        Subject saved = subjectRepository.save(subject);
        return convertToDTO(saved);
    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));
        return convertToDTO(subject);
    }

    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));

        subject.setSubjectName(dto.getSubjectName());
        subject.setCredits(dto.getCredits());
        subject.setDescription(dto.getDescription());

        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            subject.setFaculty(faculty);
        }

        Subject updated = subjectRepository.save(subject);
        return convertToDTO(updated);
    }

    private Subject convertToEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setSubjectName(dto.getSubjectName());
        subject.setCredits(dto.getCredits());
        subject.setDescription(dto.getDescription());

        if (dto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(dto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            subject.setFaculty(faculty);
        }
        return subject;
    }

    private SubjectDTO convertToDTO(Subject subject) {
        SubjectDTO dto = new SubjectDTO();
        dto.setSubjectId(subject.getSubjectId());
        dto.setSubjectName(subject.getSubjectName());
        dto.setCredits(subject.getCredits());
        dto.setDescription(subject.getDescription());

        if (subject.getFaculty() != null) {
            dto.setFacultyId(subject.getFaculty().getFacultyId());
            dto.setFacultyName(subject.getFaculty().getFacultyName());
        }
        return dto;
    }
}
