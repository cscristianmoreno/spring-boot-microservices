package com.microservice.users.models;

import java.util.List;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.microservice.users.entity.Users;

public interface UserRepositoryModel extends UserAuthenticationModel {
    void save(Users users);

    void deleteById(int id);

    Users findById(int uuid);

    List<Users> findAll();

    UserDetails findByUsername(String email) throws UsernameNotFoundException;
}
