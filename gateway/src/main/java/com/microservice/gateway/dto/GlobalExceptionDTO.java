package com.microservice.gateway.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class GlobalExceptionDTO {
    private String reason;
    private HttpStatus status;
}
