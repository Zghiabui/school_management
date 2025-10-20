package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDTO {
    private Long teacherId;

    @NotBlank(message = "Name is required")
    private String name;

    private String academicRank;

    @Pattern(regexp = "^\\d{10}$", message = "Phone must be 10 digits")
    private String phoneNumber;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    
    private int experience;
    private Long facultyId;
}