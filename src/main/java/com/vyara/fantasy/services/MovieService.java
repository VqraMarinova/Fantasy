package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.service.MovieCreateServiceModel;

public interface MovieService {
    void addNewMovie(MovieCreateServiceModel movieCreateServiceModel);
}
