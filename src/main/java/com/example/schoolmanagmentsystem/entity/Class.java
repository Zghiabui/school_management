package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long classId;

    @NotNull(message = "Môn học không được để trống")
    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @NotNull(message = "Mã giảng viên không được để trống")
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;


    @NotBlank(message = "Học kỳ không được để trống")
    @Column(length = 20)
    private String semester;

    @NotBlank(message = "Năm học không được để trống")
    @Column(name = "academic_year", length = 20)
    private String academicYear;

    @Column(length = 50)
    private String room;

    @Column(length = 100)
    private String schedule;
}