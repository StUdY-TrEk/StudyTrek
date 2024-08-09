package com.sparta.studytrek.common.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    //사용자 오류
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> UnauthorizedExceptionHandler(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }


    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> handleCustomException(CustomException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ExceptionResponse(ex.getErrorCode().getHttpStatus().value(), ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError != null ? fieldError.getDefaultMessage() : "Validation Error";
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
            .body(new ExceptionResponse(HttpStatus.FORBIDDEN.value(), errorMessage));
    }
}
