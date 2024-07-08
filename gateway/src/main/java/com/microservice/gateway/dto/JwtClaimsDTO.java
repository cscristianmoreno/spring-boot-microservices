package com.microservice.gateway.dto;

import java.util.Collection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtClaimsDTO {
    private String sub;
    private Collection<String> scope;
}
