package com.jupitters.movieApi.auth.repositories;

import com.jupitters.movieApi.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public class UserRepository extends JpaRepository<User, Long> {
}
