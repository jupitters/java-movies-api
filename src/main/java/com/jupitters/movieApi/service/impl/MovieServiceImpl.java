package com.jupitters.movieApi.service.impl;

import com.jupitters.movieApi.dto.MovieDto;
import com.jupitters.movieApi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) {
        return null;
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        return null;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        return List.of();
    }
}
