package com.jupitters.movieApi.auth.config;

import com.jupitters.movieApi.auth.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return username -> userRepository
    }
}
