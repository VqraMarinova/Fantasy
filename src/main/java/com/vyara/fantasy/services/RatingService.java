package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.ShortStory;

public interface RatingService {
    void addRatingToBook(Integer rating, Long id);

    void addRatingToMovie(Integer rating, Long id);

    void addRatingToStory(Integer rating, Long id);

    String getRatingByBook(Book book);

    String getRatingByMovie(Movie movie);

    String getRatingByStory(ShortStory story);
}
