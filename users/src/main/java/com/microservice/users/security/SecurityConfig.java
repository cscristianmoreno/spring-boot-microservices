package com.microservice.users.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    private final CustomAuthenticationManager customAuthenticationManager;

    public SecurityConfig(final CustomAuthenticationManager customAuthenticationManager) {
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        CsrfTokenRequestAttributeHandler csrfTokenRequestHandler = new CsrfTokenRequestAttributeHandler();
        csrfTokenRequestHandler.setCsrfRequestAttributeName(null);

        return (
            httpSecurity
                .authorizeHttpRequests((req) -> {
                    req.requestMatchers("/users/save", "/users/login").permitAll();
                    req.anyRequest().authenticated();
                })
                .sessionManagement((session) -> {
                    // session.requireExplicitAuthenticationStrategy(true);
                    session.sessionCreationPolicy(SessionCreationPolicy.NEVER);
                })
                .authenticationManager(customAuthenticationManager)
                .csrf((csrf) -> {
                    // csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                    // csrf.csrfTokenRequestHandler(csrfTokenRequestHandler);
                    csrf.disable();
                })
                .addFilterBefore(oncePerRequestFilter(), UsernamePasswordAuthenticationFilter.class)
                .httpBasic(basic -> {
                    basic.disable();
                })
            .build()
        );
    }

    @Bean
    OncePerRequestFilter oncePerRequestFilter() {
        return new CustomOncePerRequestFilter();
    }
}
