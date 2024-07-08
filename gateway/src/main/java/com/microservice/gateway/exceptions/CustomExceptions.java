package com.microservice.gateway.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.microservice.gateway.dto.GlobalExceptionDTO;

@ControllerAdvice
public class CustomExceptions {
    
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<GlobalExceptionDTO> exception(WebClientResponseException exception) {
        GlobalExceptionDTO globalExceptionDTO = new GlobalExceptionDTO();
        globalExceptionDTO.setReason(exception.getMessage());
        globalExceptionDTO.setStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<GlobalExceptionDTO>(globalExceptionDTO, globalExceptionDTO.getStatus());
    }
}
