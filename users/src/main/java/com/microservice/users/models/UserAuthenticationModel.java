package com.microservice.users.models;

import com.microservice.users.dto.LoginDTO;

public interface UserAuthenticationModel {
    String login(LoginDTO loginDTO);
}
