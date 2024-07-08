package com.microservice.users.services;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperService {
    
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
