package com.project.banksystemapp.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ApiError> handleUserException(UserException ex) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(ApiError.of(ex.getMessage(), HttpStatus.FORBIDDEN));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiError> handleIllegalState(IllegalStateException ex) {

        Throwable cause = ex.getCause();

        if (cause instanceof UserException userEx) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body(ApiError.of(userEx.getMessage(), HttpStatus.FORBIDDEN));
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiError.of(ex.getMessage(), HttpStatus.BAD_REQUEST));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiError.of("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    /* ========= API ERROR DTO ========= */

    public record ApiError(
            String message,
            int status,
            LocalDateTime timestamp
    ) {
        public static ApiError of(String message, HttpStatus status) {
            return new ApiError(
                    message,
                    status.value(),
                    LocalDateTime.now()
            );
        }
    }
}

