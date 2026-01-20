package com.jupitters.movieApi.repositories;

import com.jupitters.movieApi.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
