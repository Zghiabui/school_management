package com.example.schoolmanagmentsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ClassDTO {
    private Long classId;

    private String semester;        // "hk1" / "hk2" hoặc "1" / "2"
    private String academicYear;    // "2024-2025" hoặc "2025"
    private String room;
    private String schedule;        // nếu FE còn dùng thì giữ

    private Long teacherId;
    private Long subjectId;
    private String subjectName;     // tiện hiển thị FE

    // ===== Thêm 3 field mới để chống trùng giờ =====
    // 1 = Monday (hoặc 2 = Thứ 2 tùy quy ước của bạn)
    private Integer dayOfWeek;

    // Nhận chuỗi "HH:mm" hoặc "HH:mm:ss" từ FE → parse thành LocalTime
    @JsonFormat(pattern = "HH:mm[:ss]")
    private LocalTime startTime;

    @JsonFormat(pattern = "HH:mm[:ss]")
    private LocalTime endTime;
    // ===============================================
}
