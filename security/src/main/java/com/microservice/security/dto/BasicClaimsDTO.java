package com.microservice.security.dto;

import java.util.Collection;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// @Data
@Getter
@Setter
@ToString
public class BasicClaimsDTO {
    private String username;
    private Collection<String> authority;
}
