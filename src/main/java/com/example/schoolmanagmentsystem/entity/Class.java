package com.example.schoolmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalTime;

@Data
@Entity
@Table(name = "classes")
public class Class {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "class_id")              // map đúng cột PK
    private Long classId;

    // nếu bạn có Subject entity
    @NotNull(message = "Môn học không được để trống")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    // Tạm để Long; nếu có Teacher entity, đổi sang @ManyToOne tương tự Subject
    @NotNull(message = "Mã giảng viên không được để trống")
    @Column(name = "teacher_id", nullable = false)
    private Long teacherId;

    @NotBlank(message = "Học kỳ không được để trống")
    @Column(length = 20)
    private String semester;                // ví dụ: "hk1", "hk2" hoặc "1", "2"

    @NotBlank(message = "Năm học không được để trống")
    @Column(name = "academic_year", length = 20)
    private String academicYear;            // ví dụ: "2024-2025" hoặc "2025"

    @Column(length = 50)
    private String room;

    // ====== 3 cột MỚI để kiểm tra trùng/chồng lấn ======
    @NotNull(message = "Thiếu thứ trong tuần")
    @Column(name = "day_of_week", nullable = false)   // 1=Mon .. 7=Sun (hoặc 2=Thứ 2..7=CN)
    private Integer dayOfWeek;

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
