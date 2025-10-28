package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data

public class SubjectDTO {
    private Long subjectId;

    @NotBlank(message = "Tên môn không được để trống")
    private String subjectName;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Min(value = 1, message = "Số tín chỉ phải >= 1")
    @Max(value = 10, message = "Số tín chỉ phải <= 10")
    private Integer credits;

    @NotNull(message = "mô tả không được để trống")
    @Size(max = 255, message = "Mô tả phải ít hơn 255 ký tự")
    private String description;

    private Long facultyId;
    private String facultyName;

}
