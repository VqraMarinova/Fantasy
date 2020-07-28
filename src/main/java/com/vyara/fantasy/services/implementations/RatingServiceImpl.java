package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.secondary.Rating;
import com.vyara.fantasy.repositories.*;
import com.vyara.fantasy.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;
    private final ShortStoryRepository shortStoryRepository;
    private final QuestionRepository questionRepository;

    @Override
    public void addRatingToBook(Integer rating, Long id){
        Rating r = new Rating();
        r.setRating(rating);
        r.setBook(this.bookRepository.getOne(id));

        this.ratingRepository.save(r);
    }

    @Override
    public void addRatingToMovie(Integer rating, Long id){
        Rating r = new Rating();
        r.setRating(rating);
        r.setMovie(this.movieRepository.getOne(id));

        this.ratingRepository.save(r);
    }

    @Override
    public void addRatingToStory(Integer rating, Long id){
        Rating r = new Rating();
        r.setRating(rating);
        r.setStory(this.shortStoryRepository.getOne(id));

        this.ratingRepository.save(r);
    }

    @Override
    public String getRatingByBook(Book book){
       return calculateRating(this.ratingRepository.findAllByBook(book));

    }
    @Override
    public String getRatingByMovie(Movie movie){
        return calculateRating(this.ratingRepository.findAllByMovie(movie));

    }

    @Override
    public String getRatingByStory(ShortStory story){
        return calculateRating(this.ratingRepository.findAllByStory(story));

    }

    private String calculateRating(List<Rating> ratings){
        int sum = 0;

        if (ratings.size() == 0){
            return "0.0";
        }

        for (Rating rTable : ratings) {
            sum += rTable.getRating();
        }
        return formatToString(sum * 1.0/ratings.size());
    }

    private String formatToString(double v) {
        return String.format("%.1f", v);
    }
}
