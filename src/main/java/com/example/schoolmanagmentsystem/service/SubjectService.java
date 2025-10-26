package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.SubjectDTO;
import com.example.schoolmanagmentsystem.entity.Faculty;
import com.example.schoolmanagmentsystem.entity.Subject;
import com.example.schoolmanagmentsystem.exception.DuplicateDataException;
import com.example.schoolmanagmentsystem.repository.FacultyRepository;
import com.example.schoolmanagmentsystem.repository.SubjectRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private FacultyRepository facultyRepository;

    @Transactional
    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        if (subjectRepository.existsBySubjectName(subjectDTO.getSubjectName())) {
            throw new DuplicateDataException("Tên môn '" + subjectDTO.getSubjectName() + "' đã tồn tại!");
        }

        Subject subject = convertToEntity(subjectDTO);
        if (subjectDTO.getFacultyId() != null) {
            Faculty faculty = findFacultyById(subjectDTO.getFacultyId());
            subject.setFaculty(faculty);
        }

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
        Subject subject = findSubjectById(id);
        return convertToDTO(subject);
    }

    private Subject findSubjectById(Long id) {
        return subjectRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy môn học với ID: " + id));
    }

    private Faculty findFacultyById(Long id) {
        return facultyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy khoa với ID: " + id));
    }

    @Transactional
    public SubjectDTO updateSubject(Long id, SubjectDTO dto) {
        Subject existing = findSubjectById(id);

        // Chỉ check trùng khi tên thay đổi
        if (dto.getSubjectName() != null
                && !Objects.equals(existing.getSubjectName(), dto.getSubjectName())
                && subjectRepository.existsBySubjectName(dto.getSubjectName())) {
            throw new DuplicateDataException("Tên môn '" + dto.getSubjectName() + "' đã tồn tại!");
        }

        existing.setSubjectName(dto.getSubjectName());
        existing.setCredits(dto.getCredits());
        existing.setDescription(dto.getDescription());

        if (dto.getFacultyId() != null) {
            Faculty faculty = findFacultyById(dto.getFacultyId());
            existing.setFaculty(faculty);
        } else {

        }

        Subject updated = subjectRepository.save(existing);
        return convertToDTO(updated);
    }

    @Transactional
    public SubjectDTO deleteSubject(Long id) {
        Subject subject = findSubjectById(id);
        subjectRepository.delete(subject);
        return convertToDTO(subject);
    }


    private Subject convertToEntity(SubjectDTO dto) {
        Subject subject = new Subject();
        subject.setSubjectName(dto.getSubjectName());
        subject.setCredits(dto.getCredits());
        subject.setDescription(dto.getDescription());
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
