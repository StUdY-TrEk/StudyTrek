package com.sparta.studytrek.common.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    ErrorCode errorCode;

    public CustomException(ErrorCode errorCode){
        super(errorCode.getMsg());
        this.errorCode = errorCode;
    }
}
