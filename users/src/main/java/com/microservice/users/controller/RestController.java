package com.microservice.users.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.microservice.users.dto.LoginDTO;
import com.microservice.users.entity.Users;
import com.microservice.users.models.RestControllerModel;
import com.microservice.users.services.UserRepositoryService;

@Controller
@ResponseBody
@RequestMapping("/users")
public class RestController implements RestControllerModel {
    private final UserRepositoryService userRepositoryService;

    public RestController(final UserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @Override
    public void save(@RequestBody Users users) {
        // System.out.println(users.toString());
        userRepositoryService.save(users);
    }

    @Override
    public String login(@RequestBody LoginDTO authentication) {
        String token = userRepositoryService.login(authentication);
        return token;
    }

    @Override
    public List<Users> findAll() {
        return userRepositoryService.findAll();
    }
}
