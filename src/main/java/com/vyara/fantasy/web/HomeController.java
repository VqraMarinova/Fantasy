package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.ViewModels.AllQuestionsViewModel;
import com.vyara.fantasy.data.models.ViewModels.AllShortStoriesViewModel;
import com.vyara.fantasy.data.models.ViewModels.QuoteReturnModel;
import com.vyara.fantasy.services.QuestionService;
import com.vyara.fantasy.services.QuoteService;
import com.vyara.fantasy.services.ShortStoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@AllArgsConstructor
public class HomeController {

    private final QuoteService quoteService;
    private final QuestionService questionService;
    private final ShortStoryService storyService;


    @GetMapping("/home")
    public ModelAndView getHome(ModelAndView modelAndView) {
        List<AllQuestionsViewModel> questionsForUser = this.questionService.getAllQuestionsForUser();
        List<AllShortStoriesViewModel> storiesForUser = this.storyService.getAllStoriesForUser();
        modelAndView.addObject("questionsForUser", questionsForUser);
        modelAndView.addObject("questionsForUserCount", questionsForUser.size());
        modelAndView.addObject("storiesForUser", storiesForUser);
        modelAndView.addObject("storiesForUserCount", storiesForUser.size());
        modelAndView.setViewName("home");
        return modelAndView;
    }


    @GetMapping("/")
    public ModelAndView getIndex(ModelAndView modelAndView) {
        QuoteReturnModel quote = this.quoteService.getQuoteOfTheHour();
        modelAndView.addObject("quoteOfTheDay", quote);
        modelAndView.setViewName("index");
        return modelAndView;
    }


}
