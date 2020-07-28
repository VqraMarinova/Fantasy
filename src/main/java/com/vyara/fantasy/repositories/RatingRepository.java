package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.secondary.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findAllByBook(Book book);
    List<Rating> findAllByMovie(Movie movie);
    List<Rating> findAllByStory(ShortStory story);

}
