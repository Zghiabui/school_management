package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {

    private Long studentId;

    @NotBlank(message = "Mã số sinh viên không được để trống")
    private String studentCode;

    @NotBlank(message = "Tên sinh viên không được để trống")
    private String name;

    @NotNull(message = "Vui lòng chọn ngày sinh")
    private LocalDate dateOfBirth;

    @NotNull(message = "Vui lòng chọn khoa")
    private Long facultyId;

    private String facultyName;

    @Pattern(regexp = "^0\\d{9}$", message = "Số điện thoại phải bắt đầu bằng 0 và có 10 chữ số")
    private String phone;

    @Email(message = "Email không hợp lệ")
    private String email;

    private Long classId;

    private String className;
}
