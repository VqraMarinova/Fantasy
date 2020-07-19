package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.MovieCreateModel;
import com.vyara.fantasy.data.models.service.MovieCreateServiceModel;
import com.vyara.fantasy.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class MovieController {
    private final ModelMapper modelMapper;
    private final MovieService movieService;

    public MovieController(ModelMapper modelMapper, MovieService movieService) {
        this.modelMapper = modelMapper;
        this.movieService = movieService;
    }

    @GetMapping("/add-movie")
    public String getAddMovieForm() {
        return "addMovie";
    }

    @PostMapping("/add-movie")
    public String AddMovie(@Valid @ModelAttribute MovieCreateModel movieCreateModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addMovie";
        }
        movieService.addNewMovie(this.modelMapper.map(movieCreateModel, MovieCreateServiceModel.class));
        return "redirect:/";
    }

}
