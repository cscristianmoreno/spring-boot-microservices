package com.microservice.users.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.microservice.users.entity.Authority;
import com.microservice.users.roles.Roles;

import lombok.Getter;
import lombok.Setter;

public interface AuthorityRepository extends CrudRepository<Authority, Integer> {
    Collection<Authority> findByRolesIn(Collection<Roles> roles);
}
