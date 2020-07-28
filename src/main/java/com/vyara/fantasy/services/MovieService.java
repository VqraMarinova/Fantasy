package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.AllMoviesViewModel;
import com.vyara.fantasy.data.models.ViewModels.MovieViewModel;
import com.vyara.fantasy.data.models.service.MovieCreateEditServiceModel;

import java.util.List;

public interface MovieService {
    void addNewMovie(MovieCreateEditServiceModel movieCreateEditServiceModel);

    List<AllMoviesViewModel> getAllMovies();

    MovieViewModel getViewMovie(Long id);

    void editMovie(MovieCreateEditServiceModel map, Long id);

    void deleteMovie(Long id);
}
