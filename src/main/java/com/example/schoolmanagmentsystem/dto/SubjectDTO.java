package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data

public class SubjectDTO {
    private Long subjectId;

    @NotBlank(message = "Tên môn không được để trống")
    private String subjectName;
    private Integer credits;
    private String description;
    private Long facultyId;
    private String facultyName;

}
