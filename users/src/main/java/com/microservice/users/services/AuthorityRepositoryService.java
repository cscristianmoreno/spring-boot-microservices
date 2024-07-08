package com.microservice.users.services;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservice.users.entity.Authority;
import com.microservice.users.models.AuthorityRepositoryModel;
import com.microservice.users.repository.AuthorityRepository;
import com.microservice.users.roles.Roles;

@Service
public class AuthorityRepositoryService implements AuthorityRepositoryModel {

    private final AuthorityRepository authorityRepository;

    public AuthorityRepositoryService(final AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public Collection<Authority> findByRolesIn(Collection<Roles> roles) {
        return Optional.ofNullable(authorityRepository.findByRolesIn(roles)).orElseThrow();
    }
    
}
