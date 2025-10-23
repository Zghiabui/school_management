package com.example.schoolmanagmentsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.entity.Tuition;
import com.example.schoolmanagmentsystem.repository.StudentRepository;
import com.example.schoolmanagmentsystem.repository.TuitionRepository;

@Service
public class TuitionService {

    @Autowired
    private TuitionRepository tuitionRepository;

    @Autowired
    private StudentRepository studentRepository;

    // CREATE
    public TuitionDTO createTuition(TuitionDTO dto) {
        Tuition tuition = convertToEntity(dto);
        Tuition saved = tuitionRepository.save(tuition);
        return convertToDTO(saved);
    }

    // READ ALL
    public List<TuitionDTO> getAllTuitions() {
        return tuitionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // READ By ID
    public TuitionDTO getTuitionById(Long id) {
        Tuition tuition = tuitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tuition not found"));
        return convertToDTO(tuition);
    }

    // UPDATE
    public TuitionDTO updateTuition(Long id, TuitionDTO dto) {
        Tuition existing = tuitionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tuition not found"));

        existing.setStudentCode(dto.getStudentCode());
        existing.setSemester(dto.getSemester());
        existing.setAmount(dto.getAmount());
        existing.setPaymentDate(dto.getPaymentDate());
        existing.setStatus(dto.getStatus());

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            existing.setStudent(student);
        }

        Tuition updated = tuitionRepository.save(existing);
        return convertToDTO(updated);
    }

    // DELETE
    public void deleteTuition(Long id) {
        tuitionRepository.deleteById(id);
    }

    // -------------- CONVERTER METHODS ---------------- //

    private Tuition convertToEntity(TuitionDTO dto) {
        Tuition tuition = new Tuition();
        tuition.setTuitionId(dto.getTuitionId());
        tuition.setStudentCode(dto.getStudentCode());
        tuition.setSemester(dto.getSemester());
        tuition.setAmount(dto.getAmount());
        tuition.setPaymentDate(dto.getPaymentDate());
        tuition.setStatus(dto.getStatus());

        if (dto.getStudentId() != null) {
            Student student = studentRepository.findById(dto.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student not found"));
            tuition.setStudent(student);
        }

        return tuition;
    }

    private TuitionDTO convertToDTO(Tuition tuition) {
        TuitionDTO dto = new TuitionDTO();
        dto.setTuitionId(tuition.getTuitionId());
        dto.setStudentCode(tuition.getStudentCode());
        dto.setSemester(tuition.getSemester());
        dto.setAmount(tuition.getAmount());
        dto.setPaymentDate(tuition.getPaymentDate());
        dto.setStatus(tuition.getStatus());

        if (tuition.getStudent() != null) {
            dto.setStudentId(tuition.getStudent().getStudentId());
            dto.setStudentName(tuition.getStudent().getName());
        }

        return dto;
    }
}
