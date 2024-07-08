package com.microservice.users.dto;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
