package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.RatingCreateModel;
import com.vyara.fantasy.services.RatingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/rate/book/{id}")
    public String rateBook(@Valid @ModelAttribute RatingCreateModel model, @PathVariable Long id ){
        this.ratingService.addRatingToBook(model.getRating(), id);
        return "redirect:/explore/book/"+id;
    }

    @PostMapping("/rate/movie/{id}")
    public String rateMovie(@Valid @ModelAttribute RatingCreateModel model, @PathVariable Long id ){
        this.ratingService.addRatingToMovie(model.getRating(), id);
        return "redirect:/explore/movie/"+id;
    }

    @PostMapping("/rate/story/{id}")
    public String rateStory(@Valid @ModelAttribute RatingCreateModel model, @PathVariable Long id ){
        this.ratingService.addRatingToStory(model.getRating(), id);
        return "redirect:/explore/story/"+id;
    }
}
