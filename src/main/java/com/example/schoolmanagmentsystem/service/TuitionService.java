package com.example.schoolmanagmentsystem.service;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.entity.Student;
import com.example.schoolmanagmentsystem.entity.Tuition;
import com.example.schoolmanagmentsystem.exception.DuplicateDataException;
import com.example.schoolmanagmentsystem.repository.StudentRepository;
import com.example.schoolmanagmentsystem.repository.TuitionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TuitionService {

    @Autowired
    private TuitionRepository tuitionRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public TuitionDTO createTuition(TuitionDTO tuitionDTO) {
        Student student = findStudentById(tuitionDTO.getStudentId());
        tuitionRepository.findByStudentAndSemester(student, tuitionDTO.getSemester())
                .ifPresent(t -> {
                    throw new DuplicateDataException("Học phí cho sinh viên này trong học kỳ này đã tồn tại!");
                });

        Tuition tuition = convertToEntity(tuitionDTO);
        if (tuitionDTO.getStudentId() != null) {
            tuition.setStudent(student);
        }

        Tuition saved = tuitionRepository.save(tuition);
        return convertToDTO(saved);
    }

    public List<TuitionDTO> getAllTuitions() {
        return tuitionRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public TuitionDTO getTuitionById(Long id) {
        Tuition tuition = findTuitionById(id);
        return convertToDTO(tuition);
    }

    private Tuition findTuitionById(Long id) {
        return tuitionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy học phí với ID: " + id));
    }

    private Student findStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy sinh viên với ID: " + id));
    }

    @Transactional
    public TuitionDTO updateTuition(Long id, TuitionDTO dto) {
        Tuition existing = findTuitionById(id);
        Student student = findStudentById(dto.getStudentId());

        tuitionRepository.findByStudentAndSemester(student, dto.getSemester())
                .ifPresent(t -> {
                    if (!t.getTuitionId().equals(id)) {
                        throw new DuplicateDataException("Học phí cho sinh viên này trong học kỳ này đã tồn tại!");
                    }
                });

        existing.setSemester(dto.getSemester());
        existing.setAmount(dto.getAmount());
        existing.setPaymentDate(dto.getPaymentDate());
        existing.setStatus(dto.getStatus());

        if (dto.getStudentId() != null) {
            existing.setStudent(student);
        } else {

        }

        Tuition updated = tuitionRepository.save(existing);
        return convertToDTO(updated);
    }

    @Transactional
    public TuitionDTO deleteTuition(Long id) {
        Tuition tuition = findTuitionById(id);
        tuitionRepository.delete(tuition);
        return convertToDTO(tuition);
    }


    private Tuition convertToEntity(TuitionDTO dto) {
        Tuition tuition = new Tuition();
        tuition.setSemester(dto.getSemester());
        tuition.setAmount(dto.getAmount());
        tuition.setPaymentDate(dto.getPaymentDate());
        tuition.setStatus(dto.getStatus());
        return tuition;
    }

    private TuitionDTO convertToDTO(Tuition tuition) {
        TuitionDTO dto = new TuitionDTO();
        dto.setTuitionId(tuition.getTuitionId());
        dto.setStudentCode(tuition.getStudent().getStudentCode());
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
