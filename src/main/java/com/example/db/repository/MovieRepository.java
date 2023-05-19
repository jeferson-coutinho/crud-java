package com.example.db.repository;

import com.example.db.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Se necessário, adicione métodos personalizados para consultas específicas
}

