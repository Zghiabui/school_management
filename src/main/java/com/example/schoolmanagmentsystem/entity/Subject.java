package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    @NotBlank(message = "Tên môn học không được để trống")
    @Column(nullable = false, unique = true)
    private String subjectName;

    @NotNull(message = "Số tín chỉ không được để trống")
    @Min(value = 1, message = "Số tín chỉ phải >= 1")
    @Max(value = 10, message = "Số tín chỉ phải <= 10")
    private Integer credits;

    @Size(max = 255, message = "Mô tả phải ít hơn 255 ký tự")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculty_id", nullable = false)
    private Faculty faculty;
}
