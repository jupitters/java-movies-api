package com.jupitters.movieApi.auth.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.Instant;

@Entity
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    @NotBlank(message = "refresh token needed")
    private String refreshToken;
    @Column(nullable = false)
    private Instant expirationTime;

    @OneToOne
    private User user;

}
