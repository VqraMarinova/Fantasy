package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.BookCreateEditModel;
import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.services.BookService;
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
public class BookController {
    private final ModelMapper modelMapper;
    private final BookService bookService;



    @GetMapping("/add-book")
    public String getAddBookForm() {
        return "addBook";
    };

    @PostMapping("/add-book")
    public String AddBook(@Valid @ModelAttribute BookCreateEditModel bookCreateEditModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        bookService.addNewBook(this.modelMapper.map(bookCreateEditModel, BookCreateEditServiceModel.class));
        return "redirect:/home";
    };

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/book/{id}")
    public String editBook(@ModelAttribute BookCreateEditModel bookCreateEditModel, @PathVariable Long id ){
        this.bookService.editBook(this.modelMapper.map(
                bookCreateEditModel, BookCreateEditServiceModel.class
        ), id);
        return String.format("redirect:/explore/book/%s",id);
    };

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/book/{id}")
    public String deleteBook(@PathVariable Long id ) {
        this.bookService.deleteBook(id);
        return "redirect:/explore/books";
    }


}
