package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.MovieCreateEditModel;
import com.vyara.fantasy.data.models.service.MovieCreateEditServiceModel;
import com.vyara.fantasy.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String AddMovie(@Valid @ModelAttribute MovieCreateEditModel movieCreateEditModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addMovie";
        }
        movieService.addNewMovie(this.modelMapper.map(movieCreateEditModel, MovieCreateEditServiceModel.class));
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/movie/{id}")
    public String editMovie(@Valid @ModelAttribute MovieCreateEditModel movieCreateEditModel, @PathVariable Long id ){
        this.movieService.editMovie(this.modelMapper.map(
                movieCreateEditModel, MovieCreateEditServiceModel.class
        ), id);
        return String.format("redirect:/explore/movie/%s",id);

    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/movie/{id}")
    public String deleteMovie(@PathVariable Long id ) {
        this.movieService.deleteMovie(id);
        return "redirect:/explore/movies";
    }

}
