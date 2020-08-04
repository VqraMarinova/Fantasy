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

import javax.validation.Valid;
import java.io.IOException;

@Controller
@AllArgsConstructor
public class BookController {
    private final ModelMapper modelMapper;
    private final BookService bookService;
    private final CloudService cloudService;


    @ModelAttribute("bookModel")
    public BookCreateEditModel bookModel (){
        return new BookCreateEditModel();
    }

    @GetMapping("/add-book")
    public String getAddBookForm(@ModelAttribute("bookModel") BookCreateEditModel bookModel) {
        return "addBook";
    };

    @PostMapping("/add-book")
    public String AddBook(@Valid @ModelAttribute("bookModel") BookCreateEditModel bookModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addBook";
        }
        BookCreateEditServiceModel book = this.modelMapper.map(bookModel, BookCreateEditServiceModel.class);
        if (bookModel.getImage().isEmpty()) {
            book.setImage(Constants.BOOK_DEFAULT_IMAGE);
        } else {
            book.setImage(cloudService.uploadImage(bookModel.getImage()));
        }

        bookService.addNewBook(book);
        return "redirect:/home";
    };

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/book/{id}")
    public String editBook(@Valid @ModelAttribute("bookModel") BookCreateEditModel bookModel, @PathVariable Long id, AbstractBindingResult bindingResult) throws IOException {
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/edit/movie/%s",id);
        }
        BookCreateEditServiceModel book = this.modelMapper.map(bookModel, BookCreateEditServiceModel.class);
        if (!bookModel.getImage().isEmpty()) {
            book.setImage(cloudService.uploadImage(bookModel.getImage()));
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
