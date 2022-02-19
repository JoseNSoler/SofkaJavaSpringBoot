package com.sofka.project.exceptions;

import org.springframework.http.HttpStatus;

public class APIRequestException extends RuntimeException{
    private HttpStatus httpStatus;

    public APIRequestException(String message) {
        super(message);
    }

    public APIRequestException(String message, HttpStatus httpStatus){
        super(message);
        this.httpStatus = httpStatus;
    }


    public APIRequestException(String message, Throwable cause){
        super(message, cause);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
