package com.gdsc.youpick.exception.model;

import com.gdsc.youpick.exception.ErrorCode;
import lombok.Getter;

@Getter
public class JsonSyntaxException extends CustomException{

    public JsonSyntaxException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
