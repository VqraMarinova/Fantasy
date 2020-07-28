package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.models.ViewModels.AllBooksViewModel;
import com.vyara.fantasy.data.models.ViewModels.BookViewModel;
import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.repositories.BookRepository;
import com.vyara.fantasy.services.BookService;
import com.vyara.fantasy.services.CommentService;
import com.vyara.fantasy.services.RatingService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;
    private final RatingService ratingService;
    private final CommentService commentService;



    @Override
    public void addNewBook(BookCreateEditServiceModel model) throws Exception {

        //TODO
        //To check if book already exists
        Book book =  this.modelMapper.map(model, Book.class);
                prepareBookForDb(book,model);
        this.bookRepository.save(book);

    }




    @Override
    public List<AllBooksViewModel> getAllBooks(){
      List<AllBooksViewModel> allBooks = new ArrayList<>();

      this.bookRepository.findAll().forEach(b->{
          AllBooksViewModel model = modelMapper.map(b, AllBooksViewModel.class);
          model.setRating(this.ratingService.getRatingByBook(b));
          allBooks.add(model);

      });
      return allBooks;

    };
    @Override
    public BookViewModel getViewBook(Long id){
        Book book = this.bookRepository.getOne(id);
        BookViewModel model = this.modelMapper.map(book, BookViewModel.class);
        model.setRating(this.ratingService.getRatingByBook(book));
        model.setComments(this.commentService.getCommentByBook(book));
        model.setReleaseDate(book.getReleaseDate().toString());


        return model;
    }

    @Override
    public void editBook(BookCreateEditServiceModel model, Long id) {
        Book book = this.bookRepository.getOne(id);
       prepareBookForDb(book, model);
       book.setTitle(model.getTitle());
       book.setDescription(model.getDescription());
       book.setImage(model.getImage());
        this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {

        this.bookRepository.deleteById(id);

    }

    private void prepareBookForDb(Book book, BookCreateEditServiceModel model) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(model.getReleaseDate(), formatter);

        book.setReleaseDate(releaseDate);

    }

}
