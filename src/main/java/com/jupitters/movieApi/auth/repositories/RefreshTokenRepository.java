package com.jupitters.movieApi.auth.repositories;

import com.jupitters.movieApi.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
