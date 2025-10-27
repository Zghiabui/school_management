package com.example.schoolmanagmentsystem.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@Slf4j // Sử dụng Lombok để tự động tạo Logger
@RestControllerAdvice
public class ApiExceptionHandler {

    // --- CÁC LỖI 400 (BAD REQUEST) ---

    /**
     * Bắt lỗi validation (@Valid). Đây là lỗi quan trọng nhất.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.warn("Validation error: {}", errors);
        var errorResponse = new ApiErrorResponse(
                "VALIDATION_ERROR",
                "Dữ liệu đầu vào không hợp lệ",
                errors
        );
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Bắt lỗi IllegalArgumentException (ví dụ: tham số truyền vào phương thức không hợp lệ).
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleBad(IllegalArgumentException ex) {
        log.warn("Bad request: {}", ex.getMessage());
        var errorResponse = new ApiErrorResponse("BAD_REQUEST", ex.getMessage());
        return ResponseEntity.badRequest().body(errorResponse);
    }

    /**
     * Bắt lỗi trùng lặp dữ liệu do bạn tự định nghĩa.
     */
    @ExceptionHandler(DuplicateDataException.class)
    public ResponseEntity<ApiErrorResponse> handleDuplicate(DuplicateDataException ex) {
        log.warn("Duplicate data: {}", ex.getMessage());
        var errorResponse = new ApiErrorResponse("DUPLICATE_DATA", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    // --- LỖI 404 (NOT FOUND) ---

    /**
     * Bắt lỗi không tìm thấy (ví dụ: findById().orElseThrow()).
     */
    @ExceptionHandler({EntityNotFoundException.class, NoSuchElementException.class})
    public ResponseEntity<ApiErrorResponse> handleNotFound(Exception ex) {
        log.warn("Resource not found: {}", ex.getMessage());
        var errorResponse = new ApiErrorResponse(
                "NOT_FOUND",
                "Không tìm thấy tài nguyên được yêu cầu"
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }


    // --- CÁC LỖI 409 (CONFLICT) ---

    /**
     * Bắt lỗi xung đột thời gian/phòng (do bạn tự định nghĩa).
     */
    @ExceptionHandler(RoomTimeConflictException.class)
    public ResponseEntity<ApiErrorResponse> handle(RoomTimeConflictException ex) {
        log.warn("Room time conflict: {}", ex.getMessage());
        var errorResponse = new ApiErrorResponse("ROOM_TIME_CONFLICT", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    /**
     * Bắt lỗi ràng buộc dữ liệu từ DB (ví dụ: vi phạm UNIQUE constraint).
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDatabaseConflict(DataIntegrityViolationException ex) {
        String message = "Lỗi ràng buộc dữ liệu. Có thể bạn đang cố tạo trùng lặp một giá trị đã tồn tại.";
        // Lấy thông điệp lỗi cụ thể nhất từ DB
        String mostSpecificCauseMessage = ex.getMostSpecificCause().getMessage();
        log.warn("Data integrity violation: {}", mostSpecificCauseMessage);

        var errorResponse = new ApiErrorResponse("DATA_CONFLICT", message);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }


    // --- LỖI 500 (INTERNAL SERVER ERROR) - "CATCH-ALL" ---

    /**
     * Bắt tất cả các lỗi còn lại (lỗi 500) mà chưa được xử lý cụ thể.
     * Đây PHẢI là handler cuối cùng.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> handleGeneric(Exception ex) {
        // Log lỗi này ở mức ERROR vì đây là lỗi không mong muốn
        log.error("Unhandled exception occurred", ex);

        var errorResponse = new ApiErrorResponse(
                "INTERNAL_SERVER_ERROR",
                "Đã xảy ra lỗi hệ thống. Vui lòng thử lại sau."
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }


//    // --- CÁC LỖI 401 & 403 (SECURITY) ---
//
//    /**
//     * Bắt lỗi 401 - Chưa xác thực (chưa login).
//     */
//    @ExceptionHandler(AuthenticationException.class)
//    public ResponseEntity<ApiErrorResponse> handleAuthentication(AuthenticationException ex) {
//        log.warn("Authentication failed: {}", ex.getMessage());
//        var errorResponse = new ApiErrorResponse(
//                "UNAUTHENTICATED",
//                "Yêu cầu xác thực không thành công"
//        );
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//    }
//
//    /**
//     * Bắt lỗi 403 - Không có quyền.
//     */
//    @ExceptionHandler(AccessDeniedException.class)
//    public ResponseEntity<ApiErrorResponse> handleAccessDenied(AccessDeniedException ex) {
//        log.warn("Access denied: {}", ex.getMessage());
//        var errorResponse = new ApiErrorResponse(
//                "FORBIDDEN",
//                "Bạn không có quyền truy cập tài nguyên này"
//        );
//        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
//    }
//


}
