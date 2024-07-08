package com.microservice.security.controller;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservice.security.dto.BasicClaimsDTO;
import com.microservice.security.models.RestControllerModel;
import com.microservice.security.services.TokenService;
import com.microservice.security.utils.AuthenticationUtil;

@Controller
@ResponseBody
@RequestMapping("/security")
public class RestController implements RestControllerModel {
    private final TokenService tokenService;

    public RestController(final TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public String create(@RequestBody BasicClaimsDTO basicClaimsDTO) {
        String token = tokenService.encode(basicClaimsDTO);
        return token;
    }

    @Override
    public Map<String, Object> decode(@RequestBody String token) {
        Jwt jwt =  tokenService.decode(token);

        String username = jwt.getSubject();
        Collection<String> scopes = jwt.getClaim("scope");

        AuthenticationUtil.authenticate(username, scopes);
        return jwt.getClaims();
    }
}
