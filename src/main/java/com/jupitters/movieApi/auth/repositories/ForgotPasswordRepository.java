package com.jupitters.movieApi.auth.repositories;

import com.jupitters.movieApi.auth.model.ForgotPassword;
import com.jupitters.movieApi.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ForgotPasswordRepository extends JpaRepository<ForgotPassword, Long> {
    @Query("SELECT fp FROM ForgotPassword fp WHERE fp.otp = ?1 AND fp.user = ?2")
    Optional<ForgotPassword> findByOtpAndUser(Integer otp, User user);
}
