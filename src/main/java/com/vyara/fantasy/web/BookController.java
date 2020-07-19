package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.BookCreateModel;
import com.vyara.fantasy.data.models.service.BookCreateServiceModel;
import com.vyara.fantasy.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BookController {
    private final ModelMapper modelMapper;
    private final BookService bookService;

    public BookController(ModelMapper modelMapper, BookService bookService) {
        this.modelMapper = modelMapper;
        this.bookService = bookService;
    }


    @GetMapping("/add-book")
    public String getAddBookForm() {
        return "addBook";
    }

    @PostMapping("/add-book")
    public String AddBook(@Valid @ModelAttribute BookCreateModel bookCreateModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        bookService.addNewBook(this.modelMapper.map(bookCreateModel, BookCreateServiceModel.class));
        return "redirect:/";
    }

}
