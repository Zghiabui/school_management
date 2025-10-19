package com.example.schoolmanagmentsystem.dto;

import lombok.Data;

@Data
public class GradeDTO {
    private Long gradeId;
    private Float attendanceScore;
    private Float midtermScore;
    private Float finalScore;
    private String studentCode;
    private Long classId;
}