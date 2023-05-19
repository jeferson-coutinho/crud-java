package com.example.db.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.db.model.Movie;

@Service
public class MovieService {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public MovieService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // CREATE
    public void createMovie(String title, int year, String description, String genre, String image, Double rating) {
        String sql = "INSERT INTO movies (title, year, description, genre, image, rating) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, title, year, description, genre, image, rating);
        System.out.println("Filme criado com sucesso.");
    }

    // READ
    public List<Movie> getAllMovies() {
        String sql = "SELECT * FROM movies";
        List<Map<String, Object>> movies = jdbcTemplate.queryForList(sql);

        List<Movie> movieList = new ArrayList<>();

        for (Map<String, Object> movie : movies) {
            BigDecimal ratingBigDecimal = (BigDecimal) movie.get("rating");
            double ratingDouble = ratingBigDecimal.doubleValue();

            Movie movieObj = new Movie();
            movieObj.setId(((Number) movie.get("id")).longValue());
            movieObj.setTitle((String) movie.get("title"));
            movieObj.setYear((Integer) movie.get("year"));
            movieObj.setDescription((String) movie.get("description"));
            movieObj.setGenre((String) movie.get("genre"));
            movieObj.setImage((String) movie.get("image"));
            movieObj.setRating(BigDecimal.valueOf(ratingDouble));
            
            movieList.add(movieObj);
        }

        return movieList;
    }

    // UPDATE
    public void updateMovie(int id, String title, int year, String description, String genre, String image,
            Double rating) {
        String sql = "UPDATE movies SET title = ?, year = ?, description = ?, genre = ?, image = ?, rating = ? WHERE id = ?";
        jdbcTemplate.update(sql, title, year, description, genre, image, rating, id);
        System.out.println("Filme atualizado com sucesso.");
    }

    // DELETE
    public void deleteMovie(int id) {
        String sql = "DELETE FROM movies WHERE id = ?";
        jdbcTemplate.update(sql, id);
        System.out.println("Filme removido com sucesso.");
    }

    /**
     * @param id
     * @return
     */
    public Movie getMovieById(Long id) {
        String sql = "SELECT * FROM movies WHERE id = ?";
        Map<String, Object> movie = jdbcTemplate.queryForMap(sql, id);

        Movie movieObj = new Movie();
        movieObj.setId((Long) movie.get("id"));
        movieObj.setTitle((String) movie.get("title"));
        movieObj.setYear((Integer) movie.get("year"));
        movieObj.setDescription((String) movie.get("description"));
        movieObj.setGenre((String) movie.get("genre"));
        movieObj.setImage((String) movie.get("image"));
        double rating = 0.0;
        final Double ratingValue = rating;
        ((Movie) movie).setRating(BigDecimal.valueOf(ratingValue));        

        return movieObj;
    }
}