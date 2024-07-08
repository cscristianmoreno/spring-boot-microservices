package com.microservice.users.models;

import java.util.Collection;

import com.microservice.users.entity.Authority;
import com.microservice.users.roles.Roles;

public interface AuthorityRepositoryModel {
    Collection<Authority> findByRolesIn(Collection<Roles> roles);
}
