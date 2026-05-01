package com.yungwa.global.response;

import com.yungwa.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final boolean success;
    private final String errorCode;
    private final String message;

    private ErrorResponse(ErrorCode errorCode) {
        this.success = false;
        this.errorCode = errorCode.name();
        this.message = errorCode.getMessage();
    }

    public static ErrorResponse from(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
