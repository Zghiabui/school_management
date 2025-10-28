package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")              // map đúng cột PK
    private Long classId;

    @NotNull(message = "Môn học không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @NotNull(message = "Mã giảng viên không được để trống")
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @NotBlank(message = "Học kỳ không được để trống")
    @Column(length = 20)
    private String semester;                // ví dụ: "Học kỳ 1", "Học kỳ 2"

    @NotBlank(message = "Năm học không được để trống")
    @Column(name = "academic_year", length = 20)
    private String academicYear;            // ví dụ: "2024-2025"

    @Column(length = 50)
    private String room;

    @NotNull(message = "Thiếu thứ trong tuần")
    @Column(nullable = false)
    private LocalDate studyDate;

    @NotNull(message = "Thiếu giờ bắt đầu")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;                        // HH:mm:ss

    @NotNull(message = "Thiếu giờ kết thúc")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;                          // HH:mm:ss
    // =====================================================

    // Giữ lại schedule cũ (chuỗi) nếu FE đang dùng, nhưng sau có thể bỏ
    @Column(length = 100)
    private String schedule;
}
