package com.jupitters.movieApi.service;

import com.jupitters.movieApi.dto.MovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface MovieService {
    MovieDto addMovie(MovieDto movieDto, MultipartFile file);
    MovieDto getMovie(Long movieId);
    List<MovieDto> getAllMovies();
}
