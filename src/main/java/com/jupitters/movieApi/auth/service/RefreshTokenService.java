package com.jupitters.movieApi.auth.service;

import com.jupitters.movieApi.auth.model.RefreshToken;
import com.jupitters.movieApi.auth.model.User;
import com.jupitters.movieApi.auth.repositories.RefreshTokenRepository;
import com.jupitters.movieApi.auth.repositories.UserRepository;
import com.jupitters.movieApi.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            refreshToken = RefreshToken
        }

        return refreshToken
    }
}
