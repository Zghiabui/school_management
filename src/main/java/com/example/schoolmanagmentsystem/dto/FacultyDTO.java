package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class FacultyDTO {
    private Long facultyId;

    @NotBlank(message = "Tên khoa không được để trống")
    private String facultyName;

    @NotBlank(message = "Dean name is required")
    private String dean;

    @Pattern(regexp = "^0\\d{9}$", message = "Phone must be 10 digits and start is 0")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    private String description;
    private String address;

}