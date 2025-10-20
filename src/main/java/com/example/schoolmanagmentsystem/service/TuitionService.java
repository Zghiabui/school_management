package com.example.schoolmanagmentsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanagmentsystem.dto.TuitionDTO;
import com.example.schoolmanagmentsystem.entity.Tuition;
import com.example.schoolmanagmentsystem.repository.TuitionRepository;

@Service
public class TuitionService {

    @Autowired
    private TuitionRepository tuitionRepository;

    public List<TuitionDTO> getAllTuitions() {
        List<Tuition> tuitions = tuitionRepository.findAll();

        return tuitions.stream().map(t -> {
            TuitionDTO dto = new TuitionDTO();
            dto.setTuitionId(t.getTuitionId());
            dto.setStudentCode(t.getStudentCode());
            dto.setSemester(t.getSemester());
            dto.setAmount(t.getAmount());
            dto.setPaymentDate(t.getPaymentDate());
            dto.setStatus(t.getStatus());
            if (t.getStudent() != null) {
                dto.setStudentId(t.getStudent().getStudentId());
                dto.setStudentName(t.getStudent().getName());
            }
            return dto;
        }).collect(Collectors.toList());
    }
}
