package com.microservice.security.models;

import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.security.dto.BasicClaimsDTO;

public interface RestControllerModel {
    @PostMapping("/create")
    String create(@RequestBody BasicClaimsDTO basicClaimsDTO);

    @PostMapping("/decode")
    Map<String, Object> decode(@RequestBody String token);
}
