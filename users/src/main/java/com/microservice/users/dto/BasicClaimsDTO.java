package com.microservice.users.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import com.microservice.users.roles.Roles;

import lombok.Data;

@Data
public class BasicClaimsDTO {
    private String username;
    private Collection<String> authority;
}
