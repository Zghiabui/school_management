package com.example.schoolmanagmentsystem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "tuitions")
public class Tuition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tuitionId;

    @NotBlank(message = "Mã số sinh viên không được để trống")
    @Size(max = 20, message = "Mã số sinh viên không được vượt quá 20 ký tự")
    @Column(name = "student_code", length = 20, nullable = false)
    private String studentCode;

    @NotBlank(message = "Học kỳ không được để trống")
    @Size(max = 20, message = "Học kỳ không được vượt quá 20 ký tự")
    @Column(length = 20, nullable = false)
    private String semester;

    @NotNull(message = "Số tiền không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Số tiền phải lớn hơn 0")
    @Digits(integer = 10, fraction = 2, message = "Số tiền không hợp lệ")
    @Column(name = "amount", precision = 12, scale = 2, nullable = false)
    private BigDecimal amount;

    @NotNull(message = "Ngày đóng không được để trống")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @NotNull(message = "Trạng thái không được để trống")
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING) // Lưu tên enum dạng text vào DB
    private PaymentStatus status; // PAID, UNPAID, PARTIAL


    // Quan hệ với Student (optional để tránh circular dependency)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;
}
