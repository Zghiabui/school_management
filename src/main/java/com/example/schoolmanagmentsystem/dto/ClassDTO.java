package com.example.schoolmanagmentsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClassDTO {
    private Long classId;

    @NotNull(message = "Mã môn không được để trống")
    private Long subjectId;

    private String subjectName;

    @NotNull(message = "Mã giảng viên không được để trống")
    private Long teacherId;

    private String teacherName;

    @NotBlank(message = "Học kỳ không được để trống")
    private String semester;

    @NotBlank(message = "Năm học không được để trống")
    private String academicYear;

    private String room;

    private String schedule;
}
