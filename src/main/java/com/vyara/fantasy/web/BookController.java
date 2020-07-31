package com.vyara.fantasy.web;

import com.vyara.fantasy.config.Constants;
import com.vyara.fantasy.data.models.Binding.BookCreateEditModel;
import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.services.BookService;
import com.vyara.fantasy.services.CloudService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class BookController {
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final CloudService cloudService;


    @ModelAttribute("model")
    public BookCreateEditModel model (){
        return new BookCreateEditModel();
    }

    @GetMapping("/add-book")
    public String getAddBookForm(@ModelAttribute("model") BookCreateEditModel model) {
        return "addBook";
    };

    @PostMapping("/add-book")
    public String AddBook(ModelAndView modelAndView, @Valid @ModelAttribute("model") BookCreateEditModel model, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        BookCreateEditServiceModel book = this.modelMapper.map(model, BookCreateEditServiceModel.class);
        if (model.getImage().isEmpty()) {
            book.setImage(Constants.BOOK_DEFAULT_IMAGE);
        } else {
            book.setImage(cloudService.uploadImage(model.getImage()));
        }

        bookService.addNewBook(book);
        return "redirect:/home";
    };

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/book/{id}")
    public String editBook(ModelAndView modelAndView, @Valid @ModelAttribute("model") BookCreateEditModel model, @PathVariable Long id, AbstractBindingResult bindingResult ) throws IOException {
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/explore/book/%s",id);
        }
        BookCreateEditServiceModel book = this.modelMapper.map(model, BookCreateEditServiceModel.class);
        if (!model.getImage().isEmpty()) {
            book.setImage(cloudService.uploadImage(model.getImage()));
        }

        this.bookService.editBook(book, id);
        return String.format("redirect:/explore/book/%s",id);
    };

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/book/{id}")
    public String deleteBook(@PathVariable Long id ) {
        this.bookService.deleteBook(id);
        return "redirect:/explore/books";
    }


}
