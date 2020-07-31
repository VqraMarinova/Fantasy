package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTitleAndDirector(String title, String director);

}
