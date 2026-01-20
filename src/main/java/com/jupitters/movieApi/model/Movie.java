package com.jupitters.movieApi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Title must not be blank.")
    private String title;

    @Column(nullable = false)
    @NotBlank(message = "Director must not be blank.")
    private String director;

    @Column(nullable = false)
    @NotBlank(message = "Studio must not be blank.")
    private String studio;

    @ElementCollection
    @CollectionTable(name = "movie_cast")
    private Set<String> movieCast;

    @Column(nullable = false)
    @NotBlank(message = "Release year must not be blank.")
    private Integer releaseYear;

    @Column(nullable = false)
    @NotBlank(message = "Poster must not be blank.")
    private String poster;
}
