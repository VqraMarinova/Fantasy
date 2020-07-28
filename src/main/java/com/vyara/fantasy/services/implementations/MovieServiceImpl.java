package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.models.ViewModels.AllMoviesViewModel;
import com.vyara.fantasy.data.models.ViewModels.MovieViewModel;
import com.vyara.fantasy.data.models.service.MovieCreateEditServiceModel;
import com.vyara.fantasy.repositories.MovieRepository;
import com.vyara.fantasy.services.CommentService;
import com.vyara.fantasy.services.MovieService;
import com.vyara.fantasy.services.RatingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;
    private final RatingService ratingService;
    private final CommentService commentService;




    @Override
    public void addNewMovie(MovieCreateEditServiceModel model) {

        //TODO
        //To check if movie already exists
        Movie movie = this.modelMapper.map(model, Movie.class);
        generateReleaseDate(movie, model);

        this.movieRepository.save(movie);

    }


    @Override
    public List<AllMoviesViewModel> getAllMovies(){
        List<AllMoviesViewModel> allMovies = new ArrayList<>();

        this.movieRepository.findAll().forEach(m->{
            AllMoviesViewModel model = modelMapper.map(m, AllMoviesViewModel.class);
            model.setRating(this.ratingService.getRatingByMovie(m));
            allMovies.add(model);

        });
        return allMovies;

    };
    @Override
    public MovieViewModel getViewMovie(Long id){
        Movie movie = this.movieRepository.getOne(id);
        MovieViewModel model = this.modelMapper.map(movie, MovieViewModel.class);
        model.setRating(this.ratingService.getRatingByMovie(movie));
        model.setComments(this.commentService.getCommentByMovie(movie));
        model.setReleaseDate(movie.getReleaseDate().toString());


        return model;
    }

    @Override
    public void editMovie(MovieCreateEditServiceModel model, Long id) {
        Movie movie = this.movieRepository.getOne(id);
        generateReleaseDate(movie, model);
        movie.setTitle(model.getTitle());
        movie.setDescription(model.getDescription());
        movie.setDirector(model.getDirector());
        movie.setCast(model.getCast());
        movie.setTrailerLink(model.getTrailerLink());
        this.movieRepository.save(movie);

    }

    @Override
    public void deleteMovie(Long id) {
        this.movieRepository.delete(this.movieRepository.getOne(id));
    }

    private void generateReleaseDate(Movie movie, MovieCreateEditServiceModel model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(model.getReleaseDate(), formatter);

        movie.setReleaseDate(releaseDate);
    }
}

