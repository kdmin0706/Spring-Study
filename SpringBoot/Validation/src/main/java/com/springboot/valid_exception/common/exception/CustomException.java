package com.springboot.valid_exception.common.exception;

import com.springboot.valid_exception.common.controller.Constants;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends Exception {

    private final Constants.ExceptionClass exceptionClass;
    private final HttpStatus httpStatus;

    public CustomException(Constants.ExceptionClass exceptionClass
            , HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

}
