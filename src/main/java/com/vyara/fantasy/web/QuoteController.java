package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.QuoteCreateEditModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.services.QuoteService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class QuoteController {
    private final ModelMapper modelMapper;
    private final QuoteService quoteService;



    @GetMapping("/add-quote")
    public String getAddQuoteForm() {
        return "addQuote";
    }

    @PostMapping("/add-quote")
    public String AddQuote(@Valid @ModelAttribute QuoteCreateEditModel quoteCreateEditModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addQuote";
        }
        quoteService.addNewQuote(this.modelMapper.map(quoteCreateEditModel, QuoteCreateEditServiceModel.class));
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/quote/{id}")
    public String editQuote(@Valid @ModelAttribute QuoteCreateEditModel quoteCreateEditModel, @PathVariable Long id ){
        this.quoteService.editQuote(this.modelMapper.map(
                quoteCreateEditModel, QuoteCreateEditServiceModel.class
        ), id);
        return "redirect:/explore/quotes";
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/quote/{id}")
    public String deleteQQuote(@PathVariable Long id ) {
        this.quoteService.deleteQuote(id);
        return "redirect:/explore/quotes";
    }

}
