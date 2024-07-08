package com.microservice.users.dto;

import java.time.Instant;
import java.util.Collection;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DecodeClaimsDTO {
    private String sub;
    private Collection<String> scope;
}
