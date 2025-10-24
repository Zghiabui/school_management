package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    @NotBlank(message = "Vui lòng nhập mã số sinh viên (MSSV)")
    @Size(max = 20, message = "Mã số sinh viên không được vượt quá 20 ký tự")
    @Column(name = "student_code", length = 20, nullable = false, unique = true)
    private String studentCode;

    @NotBlank(message = "Vui lòng nhập họ và tên sinh viên")
    @Size(max = 100, message = "Tên sinh viên không được vượt quá 100 ký tự")
    private String name;

    @NotNull(message = "Vui lòng chọn ngày sinh")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số")
    @Column(length = 15)
    private String phone;

    @Email(message = "Email không hợp lệ, vui lòng nhập đúng định dạng")
    @Size(max = 100, message = "Email không được vượt quá 100 ký tự")
    @Column(length = 100)
    private String email;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Class clazz;
}
