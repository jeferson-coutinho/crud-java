package com.example.db.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.db.model.Movie;
import com.example.db.repository.MovieRepository;
import com.example.db.service.interfaces.IMovieService;

@Service
public class MovieService implements IMovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // CREATE
    public void createMovie(String title, int year, String description, String genre, String image, BigDecimal rating) {
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setYear(year);
        movie.setDescription(description);
        movie.setGenre(genre);
        movie.setImage(image);
        movie.setRating(rating);
        movieRepository.save(movie);
        System.out.println("Filme criado com sucesso.");
    }

    // READ
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // UPDATE
    public void updateMovie(Long id, String title, int year, String description, String genre, String image,
            BigDecimal rating) {
        Movie movie = movieRepository.findById(id).orElse(null);
        if (movie != null) {
            movie.setTitle(title);
            movie.setYear(year);
            movie.setDescription(description);
            movie.setGenre(genre);
            movie.setImage(image);
            movie.setRating(rating);
            movieRepository.save(movie);
            System.out.println("Filme atualizado com sucesso.");
        } else {
            System.out.println("Filme não encontrado.");
        }
    }

    // DELETE
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
        System.out.println("Filme removido com sucesso.");
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id).orElse(null);
    }
}
