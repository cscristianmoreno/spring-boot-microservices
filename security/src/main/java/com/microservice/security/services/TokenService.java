package com.microservice.security.services;

import java.time.Instant;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.microservice.security.dto.BasicClaimsDTO;

@Service
public class TokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public TokenService(final JwtEncoder jwtEncoder, final JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public String encode(BasicClaimsDTO basicClaimsDTO) {
        String subject = basicClaimsDTO.getUsername();

        Instant now = Instant.now();
        long expire = 15L;

        Collection<String> scopes = basicClaimsDTO.getAuthority().stream().toList();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet
        .builder()
            .issuer("localhost")
            .issuedAt(now)
            .subject(subject)
            .expiresAt(now.plusSeconds(expire))
            .claim("scope", scopes)
        .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
    }

    public Jwt decode(String token) {
        return jwtDecoder.decode(token);
    }
}
