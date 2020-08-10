package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.secondary.Rating;

import java.util.List;

public interface RatingService {
    void addRatingToBook(Integer rating, Long id);

    void addRatingToMovie(Integer rating, Long id);

    void addRatingToStory(Integer rating, Long id);

    String getRatingByBook(Book book);

    String getRatingByMovie(Movie movie);

    String getRatingByStory(ShortStory story);


    List<Rating> getRatingsForCurrentUser();
}
