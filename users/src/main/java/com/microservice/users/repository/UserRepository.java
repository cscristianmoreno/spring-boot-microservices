package com.microservice.users.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.microservice.users.entity.Users;
import jakarta.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<Users, Integer> {
    void deleteById(int id);

    List<Users> findAll();

    Optional<Users> findByEmail(String email);
}
