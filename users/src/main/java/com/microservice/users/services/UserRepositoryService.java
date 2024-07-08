package com.microservice.users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.microservice.users.dto.LoginDTO;
import com.microservice.users.entity.Users;
import com.microservice.users.models.UserRepositoryModel;
import com.microservice.users.repository.UserRepository;
import com.microservice.users.security.CustomAuthenticationManager;
import com.microservice.users.security.CustomUserDetails;

@Service
public class UserRepositoryService implements UserRepositoryModel {
    private final UserRepository userRepository;
    private final CustomAuthenticationManager customAuthenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RestTemplateService restTemplateService;

    public UserRepositoryService(final UserRepository userRepository, final CustomAuthenticationManager customAuthenticationManager,
    final AuthorityRepositoryService authorityRepositoryService,
    final PasswordEncoder passwordEncoder, final RestTemplateService restTemplateService) {
        this.userRepository = userRepository;
        this.customAuthenticationManager = customAuthenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.restTemplateService = restTemplateService;
    }

    @Override
    public void save(Users users) {
        String password = passwordEncoder.encode(users.getPassword());
        users.setPassword(password);
        userRepository.save(users);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users findById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Users> findAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails findByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> users = userRepository.findByEmail(email);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return new CustomUserDetails(users.get());
    }

    @Override
    public String login(LoginDTO loginDTO) {
        Authentication userPasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = customAuthenticationManager.authenticate(userPasswordAuthenticationToken);
        String token = restTemplateService.obtainToken(authentication);
        return token;
    }
}
