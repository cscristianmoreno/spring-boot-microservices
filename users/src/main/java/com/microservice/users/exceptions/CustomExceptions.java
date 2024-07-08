package com.microservice.users.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptions {
    
    @ExceptionHandler(Exception.class)
    public String exception(Exception exception) {
        return exception.getMessage();
    }
}
