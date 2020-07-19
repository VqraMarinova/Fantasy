package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.models.service.MovieCreateServiceModel;
import com.vyara.fantasy.repositories.MovieRepository;
import com.vyara.fantasy.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class MovieServiceImpl implements MovieService {
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(ModelMapper modelMapper, MovieRepository movieRepository) {
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }


    @Override
    public void addNewMovie(MovieCreateServiceModel movieCreateServiceModel) {

        //TODO
        //To check if book already exists


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(movieCreateServiceModel.getReleaseDate(), formatter);
        Movie movie = this.modelMapper.map(movieCreateServiceModel, Movie.class);
        movie.setReleaseDate(releaseDate);

        this.movieRepository.save(movie);

    }
}

