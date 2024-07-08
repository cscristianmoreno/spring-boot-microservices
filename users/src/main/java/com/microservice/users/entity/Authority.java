package com.microservice.users.entity;

import java.util.Collection;

import com.microservice.users.roles.Roles;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Authority extends BaseEntity {
    @Enumerated(EnumType.STRING)
    private Collection<Roles> roles;
}
