package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.secondary.Author;
import com.vyara.fantasy.data.models.service.BookCreateServiceModel;
import com.vyara.fantasy.repositories.BookRepository;
import com.vyara.fantasy.services.AuthorService;
import com.vyara.fantasy.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Autowired
    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository, AuthorService authorService) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    public void addNewBook(BookCreateServiceModel bookCreateServiceModel) throws Exception {

        //TODO
        //To check if book already exists

        Author author = this.authorService.findAuthorByName(bookCreateServiceModel.getAuthor());

       if (author == null){
    author = this.authorService.createAuthor(bookCreateServiceModel.getAuthor());

       }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(bookCreateServiceModel.getReleaseDate(), formatter);
        Book book = this.modelMapper.map(bookCreateServiceModel, Book.class);
        book.setReleaseDate(releaseDate);
        book.setAuthor(author);
        this.bookRepository.save(book);

    }
}
