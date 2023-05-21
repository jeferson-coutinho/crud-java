package com.example.db.service.interfaces;

import java.util.List;
import java.math.BigDecimal;

import com.example.db.model.Movie;

public interface IMovieService {
    void createMovie(String title, int year, String description, String genre, String image, BigDecimal rating);

    List<Movie> getAllMovies();

    void updateMovie(Long id, String title, int year, String description, String genre, String image,
            BigDecimal rating);

    void deleteMovie(Long id);

    Movie getMovieById(Long id);
}
