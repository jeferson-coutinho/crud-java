package com.example.db;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.example.db.model.Movie;
import com.example.db.service.implement.MovieService;

@SpringBootApplication
public class Main {

    private final MovieService movieService;

    public Main(MovieService movieService) {
        this.movieService = movieService;
    }

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Main.class, args);
        Main main = context.getBean(Main.class);
        main.run();
    }

    public void run() {
        List<Movie> movies = movieService.getAllMovies();
        for (Movie movie : movies) {
            System.out.println(movie.toString());
        }
    }
}
