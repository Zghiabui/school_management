package com.example.schoolmanagmentsystem.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;
import java.util.Map;

/**
 * Cấu trúc trả về chuẩn khi API có lỗi.
 * @param errorCode Mã lỗi nghiệp vụ (ví dụ: "VALIDATION_ERROR", "NOT_FOUND")
 * @param message Thông điệp lỗi chi tiết
 * @param timestamp Thời gian xảy ra lỗi
 * @param validationErrors Map chứa lỗi của từng trường (chỉ dùng cho lỗi validation)
 */
@JsonInclude(JsonInclude.Include.NON_NULL) // Sẽ không serialize các trường bị null
public record ApiErrorResponse(
        String errorCode,
        String message,
        Instant timestamp,
        Map<String, String> validationErrors
) {
    // Constructor cho các lỗi thông thường (không có validation errors)
    public ApiErrorResponse(String errorCode, String message) {
        this(errorCode, message, Instant.now(), null);
    }

    // Constructor cho lỗi validation
    public ApiErrorResponse(String errorCode, String message, Map<String, String> validationErrors) {
        this(errorCode, message, Instant.now(), validationErrors);
    }
}