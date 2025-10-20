package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "grades")
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GradeID")
    private Long gradeId;

    @Column(name = "AttendanceScore")
    private Float attendanceScore;

    @Column(name = "MidtermScore")
    private Float midtermScore;

    @Column(name = "FinalScore")
    private Float finalScore;

    @ManyToOne
    @JoinColumn(name = "StudentCode", referencedColumnName = "student_code")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "ClassID")
    private Class aClass;
}