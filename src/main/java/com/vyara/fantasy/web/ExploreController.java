package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class ExploreController {

    @ModelAttribute("quoteModel")
    public QuoteCreateEditModel quoteModel (){
        return new QuoteCreateEditModel();
    }

    @ModelAttribute("bookModel")
    public BookCreateEditModel bookModel (){ return new BookCreateEditModel(); }

    @ModelAttribute("movieModel")
    public MovieCreateEditModel movieModel (){
        return new MovieCreateEditModel();
    }

    @ModelAttribute("questionModel")
    public QuestionCreateEditModel questionModel (){
        return new QuestionCreateEditModel();
    }

    @ModelAttribute("storyModel")
    public ShortStoryCreateEditModel storyModel (){
        return new ShortStoryCreateEditModel();
    }

    @ModelAttribute("commentModel")
    public CommentCreateEditModel commentModel (){
        return new CommentCreateEditModel();
    }


    @GetMapping("/explore/{items}")
    public String getExploreItemsScreen(@PathVariable String items) {
        return "exploreAll";
    }

    @GetMapping("/explore/quote/{id}")
    public String getQuote(@ModelAttribute("quoteModel") QuoteCreateEditModel quoteModel, @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id) {
        return "exploreElement";
    }

    @GetMapping("/explore/book/{id}")
    public String getBook(@ModelAttribute("bookModel") BookCreateEditModel bookModel, @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id) {
        return "exploreElement";
    }

    @GetMapping("/explore/movie/{id}")
    public String getMovie(@ModelAttribute("movieModel") MovieCreateEditModel movieModel, @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id) {
        return "exploreElement";
    }

    @GetMapping("/explore/story/{id}")
    public String getStory(@ModelAttribute("storyModel") ShortStoryCreateEditModel storyModel, @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id) {
        return "exploreElement";
    }

    @GetMapping("/explore/question/{id}")
    public String getQuestion(@ModelAttribute("questionModel") QuestionCreateEditModel questionModel, @ModelAttribute("commentModel") CommentCreateEditModel commentModel, @PathVariable Long id) {
        return "exploreElement";
    }
}
