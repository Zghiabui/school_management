package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "faculties")

public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long facultyId;

    @Column(nullable = false, unique = true)
    private String facultyName;
    private String dean;
    private String phone;
    private String email;

    @Column(nullable = true)
    private String description;

}
