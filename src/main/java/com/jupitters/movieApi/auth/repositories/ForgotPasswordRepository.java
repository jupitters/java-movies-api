package com.jupitters.movieApi.auth.repositories;

import com.jupitters.movieApi.auth.model.ForgotPassword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
}
