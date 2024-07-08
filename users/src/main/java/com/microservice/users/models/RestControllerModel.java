package com.microservice.users.models;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservice.users.dto.LoginDTO;
import com.microservice.users.entity.Users;

public interface RestControllerModel {
    @PostMapping("/save")
    void save(@RequestBody Users users);

    @PostMapping("/login")
    String login(@RequestBody LoginDTO authentication);

    @GetMapping("/all")
    List<Users> findAll();
}
