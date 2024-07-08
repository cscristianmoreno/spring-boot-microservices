package com.microservice.security.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.microservice.security.dto.JwtExceptionDTO;

@ControllerAdvice
public class CustomExceptions {
    
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<JwtExceptionDTO> jwtException(JwtException exception) {
        JwtExceptionDTO jwtExceptionDTO = new JwtExceptionDTO();
        jwtExceptionDTO.setMessage(exception.getMessage());
        jwtExceptionDTO.setHttpStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<JwtExceptionDTO>(jwtExceptionDTO, jwtExceptionDTO.getHttpStatus());
    }
}
