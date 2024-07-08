package com.microservice.users.security;

import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.users.services.UserRepositoryService;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {
    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationManager(@Lazy final UserRepositoryService userRepositoryService, final PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        AuthenticationProvider authenticationProvider = new CustomAuthenticationProvider(userRepositoryService, passwordEncoder);
        Authentication result = authenticationProvider.authenticate(authentication);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(result);
        return securityContext.getAuthentication();
    }
    
}
