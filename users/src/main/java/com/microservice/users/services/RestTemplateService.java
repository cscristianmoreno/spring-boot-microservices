package com.microservice.users.services;

import java.util.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.users.dto.BasicClaimsDTO;

@Service
public class RestTemplateService {
    private final RestTemplate restTemplate;

    public RestTemplateService(final RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtainToken(Authentication authentication) {
        String username = authentication.getName();
        Collection<String> authority = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();

        BasicClaimsDTO basicClaimsDTO = new BasicClaimsDTO();
        basicClaimsDTO.setUsername(username);
        basicClaimsDTO.setAuthority(authority);

        HttpEntity<BasicClaimsDTO> httpEntity = new HttpEntity<BasicClaimsDTO>(basicClaimsDTO);
        ResponseEntity<String> token = restTemplate.exchange("lb://security/security/create", HttpMethod.POST, httpEntity, String.class);
        return token.getBody();
    }
}
