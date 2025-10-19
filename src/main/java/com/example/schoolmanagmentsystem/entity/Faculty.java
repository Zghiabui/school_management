package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "faculties")

public class Faculty {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @NotBlank(message = "Faculty name is required")
    @Column(nullable = false, unique = true)
    private String facultyName;

    @NotBlank(message = "Dean name is required")
    private String dean;

    @Pattern(regexp = "^0\\d{9}$", message = "Phone must be 10 digits and start is 0")
    private String phone;

    @Email(message = "Email should be valid")
    private String email;

    @Size(max = 255, message = "Description must be less than 255 characters")
    private String description;

    @NotBlank(message = "Address is required")
    private String address;


}
