package com.gdsc.youpick.exception.model;

import com.gdsc.youpick.exception.ErrorCode;

public class BadRequestException extends CustomException {
    public BadRequestException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
