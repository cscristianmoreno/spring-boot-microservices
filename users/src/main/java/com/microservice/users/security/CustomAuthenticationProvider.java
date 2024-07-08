package com.microservice.users.security;

import java.util.Collection;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.microservice.users.services.UserRepositoryService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;

    public CustomAuthenticationProvider(final UserRepositoryService userRepositoryService, final PasswordEncoder passwordEncoder) {
        this.userRepositoryService = userRepositoryService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userRepositoryService.findByUsername(username);
        
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Authentication incorrect!!");
        }
        
        password = user.getPassword();
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();    
        
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password, authorities);
        return auth;   
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
    }
    
}
