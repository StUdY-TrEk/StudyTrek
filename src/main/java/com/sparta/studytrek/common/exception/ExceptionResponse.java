package com.sparta.studytrek.common.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private int statusCode;
    private String message;

    public ExceptionResponse(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
