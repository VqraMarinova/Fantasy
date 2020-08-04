package com.vyara.fantasy.services.validation;

import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.MovieCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import com.vyara.fantasy.repositories.BookRepository;
import com.vyara.fantasy.repositories.MovieRepository;
import com.vyara.fantasy.repositories.QuoteRepository;
import com.vyara.fantasy.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EntityValidatorImpl implements EntityValidator {
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;
    private final QuoteRepository quoteRepository;


    @Override
    public boolean isUserValid(UserRegisterServiceModel user) {
        return !userRepository.existsByUsername(user.getUsername()) &&
                !userRepository.existsByEmail(user.getEmail());
    }


    @Override
    public boolean isBookValid(BookCreateEditServiceModel model){
        return !this.bookRepository.existsByAuthorAndTitle(model.getAuthor(), model.getTitle());

    }

    @Override
    public boolean isMovieValid(MovieCreateEditServiceModel model){
        return !this.movieRepository.existsByTitleAndDirector(model.getTitle(), model.getDirector());
    }

    @Override
    public boolean isQuoteValid(QuoteCreateEditServiceModel model){
        return !this.quoteRepository.existsByAuthorAndContent(model.getAuthor(), model.getContent());
    }

    @Override
    public boolean arePasswordsValid(String password, String secondPassword) {
        return password.equals(secondPassword);
    }


}
