package com.jupitters.movieApi.service.impl;

import com.jupitters.movieApi.dto.MovieDto;
import com.jupitters.movieApi.model.Movie;
import com.jupitters.movieApi.repositories.MovieRepository;
import com.jupitters.movieApi.service.FileService;
import com.jupitters.movieApi.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final FileService fileService;

    @Value("${projects.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        if(Files.exists(Paths.get(path + File.separator + file.getOriginalFilename()))){
            throw new RuntimeException("File already exists!");
        }

        String uploadedFileName = fileService.uploadFile(path, file);
        movieDto.setPoster(uploadedFileName);

        Movie movie  = new Movie(
                null,
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );
        Movie savedMovie = movieRepository.save(movie);
        String posterUrl = baseUrl + "/file/" + uploadedFileName;

        MovieDto response =  new MovieDto(
                savedMovie.getId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public MovieDto getMovie(Long movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found!"));
        String posterUrl = baseUrl + "/file/" + movie.getPoster();
        MovieDto response =  new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        List<MovieDto> moviesDtos = new ArrayList<>();

        for(Movie movie : movies){
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto =  new MovieDto(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            );
            moviesDtos.add(movieDto);
        }
        return moviesDtos;
    }

    @Override
    public MovieDto updateMovie(Long movieId, MovieDto movieDto, MultipartFile file) throws IOException {
        Movie mv = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found!"));
        String fileName = mv.getPoster();
        if(file != null){
            Files.deleteIfExists(Paths.get(path + File.separator + fileName));
            fileName = fileService.uploadFile(path, file);
        }

        movieDto.setPoster(fileName);

        Movie movie = new Movie(
                mv.getId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );
        Movie updatedMovie = movieRepository.save(movie);
        String posterUrl = baseUrl + "/file/" + fileName;

        MovieDto response = new MovieDto(
                movie.getId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public String deleteMovie(Long movieId) {
        return "";
    }
}
