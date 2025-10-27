package com.example.schoolmanagmentsystem.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class FacultyDTO {
    private Long facultyId;

    @NotBlank(message = "Tên khoa không được để trống")
    private String facultyName;

    @NotBlank(message = "Dean name is required")
    private String dean;

    @Column(unique = true)
    @Pattern(regexp = "^0\\d{9}$", message = "Phone must be 10 digits and start is 0")
    private String phone;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "address must be less than 255 characters")
    private String address;

}