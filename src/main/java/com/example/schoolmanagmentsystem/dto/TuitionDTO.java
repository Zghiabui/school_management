package com.example.schoolmanagmentsystem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.schoolmanagmentsystem.entity.PaymentStatus;
import lombok.Data;

@Data
public class TuitionDTO {
    private Long tuitionId;
    private String studentCode;
    private String semester;
    private BigDecimal amount;
    private LocalDate startDate;   // mới
    private LocalDate endDate;     // mới
    private PaymentStatus status;
    private Long studentId;
    private String studentName;
}
