package com.example.schoolmanagmentsystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class RoomTimeConflictException extends RuntimeException {
    public RoomTimeConflictException(String message) {
        super(message);
    }
}
