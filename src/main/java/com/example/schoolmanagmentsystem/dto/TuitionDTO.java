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
    private LocalDate paymentDate;
    private PaymentStatus status;  
    private Long studentId;
    private String studentName;
}
