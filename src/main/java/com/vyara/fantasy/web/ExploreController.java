package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.ViewModels.QuoteReturnModel;
import com.vyara.fantasy.services.QuoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class ExploreController {
    private final QuoteService quoteService;


    @GetMapping("/explore/{items}")
    public String getExploreItemsScreen(@PathVariable String items) {
        return "exploreAll";
    }

    @GetMapping("/explore")
    public ModelAndView getExploreScreen(ModelAndView modelAndView) {
        QuoteReturnModel quote = this.quoteService.getQuoteOfTheDay();
        modelAndView.addObject("quoteOfTheDay", quote);
        modelAndView.setViewName("exploreAll");
        return modelAndView;
    }

    @GetMapping("/explore/{item}/{id}")
    public String getExploreItemScreen(@PathVariable String item, @PathVariable Long id) {
        return "exploreElement";
    }
}
