package com.microservice.security.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class JwtExceptionDTO {
    private HttpStatus httpStatus;
    private String message;
}
