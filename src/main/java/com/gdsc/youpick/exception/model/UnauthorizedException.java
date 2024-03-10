package com.gdsc.youpick.exception.model;

import com.gdsc.youpick.exception.ErrorCode;

public class UnauthorizedException extends CustomException{

    public UnauthorizedException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
