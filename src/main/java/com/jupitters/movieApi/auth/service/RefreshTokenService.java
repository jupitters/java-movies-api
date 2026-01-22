package com.jupitters.movieApi.auth.service;

import com.jupitters.movieApi.auth.model.RefreshToken;
import com.jupitters.movieApi.auth.model.User;
import com.jupitters.movieApi.auth.repositories.RefreshTokenRepository;
import com.jupitters.movieApi.auth.repositories.UserRepository;
import com.jupitters.movieApi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken createRefreshToken(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Username not found!"));
        RefreshToken refreshToken = user.getRefreshToken();
        if(refreshToken == null){
            long refreshTOkenValidity = 1000 * 5 * 60 * 60;
            refreshToken = RefreshToken.builder()
                    .refreshToken(UUID.randomUUID().toString())
                    .expirationTime(Instant.now().plusMillis(refreshTOkenValidity))
                    .user(user)
                    .build();
            refreshTokenRepository.save(refreshToken);
        }

        return refreshToken;
    }

    public RefreshToken verifyRefreshToken(String refreshToken){
        refreshTokenRepository.
    }
}
