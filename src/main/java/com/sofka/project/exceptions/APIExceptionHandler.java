package com.sofka.project.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(value = {APIRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(APIRequestException e){
        // Crear mensaje clase formateado en base a una excepcion
        ApiException apiException = new ApiException(
                e.getMessage(),
                e.getHttpStatus(),
                ZonedDateTime.now(ZoneId.of("Z"))
        );

        return new ResponseEntity<>(apiException, e.getHttpStatus());

    }
}
