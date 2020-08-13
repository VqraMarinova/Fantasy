package com.vyara.fantasy.services.validation;

import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.MovieCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;

public interface EntityValidator {
    boolean isUserValid(UserRegisterServiceModel user);

    boolean isEmailValid(String newEmail, String confirmEmail);

    boolean isBookValid(BookCreateEditServiceModel model);

    boolean isMovieValid(MovieCreateEditServiceModel model);

    boolean isQuoteValid(QuoteCreateEditServiceModel model);

    boolean arePasswordsValid(String password, String confirmPassword);
}
