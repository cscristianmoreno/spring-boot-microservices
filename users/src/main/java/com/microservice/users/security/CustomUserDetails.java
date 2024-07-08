package com.microservice.users.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.microservice.users.entity.Users;
import com.microservice.users.roles.Roles;

import lombok.ToString;

@ToString
public class CustomUserDetails implements UserDetails {

    private final Users users;

    public CustomUserDetails(final Users users) {
        this.users = users;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<Roles> roles = users.getAuthority().getRoles();
        return AuthorityUtils.createAuthorityList(roles.stream().map(Roles::name).toList());
    }

    @Override
    public String getPassword() {
        return users.getPassword();
    }

    @Override
    public String getUsername() {
        return users.getEmail();
    }
    
}
