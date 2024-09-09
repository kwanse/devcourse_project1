package com.grepp.global.advice;

import com.grepp.global.exception.NoDataException;
import com.grepp.global.exception.TimeExceedException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoDataException.class)
    public ResponseEntity<ErrorResponse> handleNoDataException(NoDataException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(TimeExceedException.class)
    public ResponseEntity<ErrorResponse> handleTimeExceedException(TimeExceedException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
