package com.jupitters.movieApi.controller;

import com.jupitters.movieApi.dto.MovieDto;
import com.jupitters.movieApi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movie")
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/add")
    public ResponseEntity<MovieDto> addMovieHandler(@RequestPart MultipartFile file, @RequestPart String movieDto) throws IOException {
        MovieDto dto = convertToMovieDto(movieDto);
        MovieDto response = movieService.addMovie(dto, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long movieId){
        return ResponseEntity.ok(movieService.getMovie(movieId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDto>> getAllMovies(){
        return ResponseEntity.ok(movieService.getAllMovies());
    }
    
    @PutMapping("/{movieId}/update")
    public ResponseEntity<MovieDto> updateMovie(@PathVariable Long movieId, @RequestPart MultipartFile file, @RequestPart String movieDtoObj) throws IOException {
        if(file.isEmpty()) file = null;
        MovieDto movieDto = convertToMovieDto(movieDtoObj);
        return ResponseEntity.ok(movieService.updateMovie(movieId, movieDto, file));
    }

    private MovieDto convertToMovieDto(String movieDtoObj){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(movieDtoObj, MovieDto.class);
    }
}
