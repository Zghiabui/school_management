package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FacultyDTO {
    private Long facultyId;

    @NotBlank(message = "Tên danh mục không được để trống")
    private String facultyName;
    private String dean;
    private String phone;
    private String email;
    private String description;
    private String address;

}