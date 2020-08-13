package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.constants.Constants;
import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.secondary.Rating;
import com.vyara.fantasy.data.models.ViewModels.AllBooksViewModel;
import com.vyara.fantasy.data.models.ViewModels.BookViewModel;
import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;
import com.vyara.fantasy.errors.EntityAlreadyExistsException;
import com.vyara.fantasy.repositories.BookRepository;
import com.vyara.fantasy.services.BookService;
import com.vyara.fantasy.services.CloudService;
import com.vyara.fantasy.services.CommentService;
import com.vyara.fantasy.services.RatingService;
import com.vyara.fantasy.services.validation.EntityValidator;
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
    private final EntityValidator entityValidator;
    private final CloudService cloudService;




    @Override
    public void addNewBook(BookCreateEditServiceModel model) throws Exception {
        if (!this.entityValidator.isBookValid(model)){
            throw new EntityAlreadyExistsException ("Book already exists");
        }

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
        if (model.getImage().contains("org.springframework.web.multipart.support.StandardMultipartHttpServletRequest")){
            model.setImage(Constants.BOOK_DEFAULT_IMAGE.toString());
        }

        List<Rating> ratings = this.ratingService.getRatingsForCurrentUser();
        model.setCanVote(true);


        ratings.forEach(rating -> {
            if (rating.getBook() == book){
                model.setCanVote(false);
            }
        });

        return model;
    }

    @Override
    public void editBook(BookCreateEditServiceModel model, Long id) {
        Book book = this.bookRepository.getOne(id);
       prepareBookForDb(book, model);
       book.setTitle(model.getTitle());
       book.setDescription(model.getDescription());
       if (model.getImage()!=null && book.getImage()!=null) {
           this.cloudService.deleteImageByUrl(book.getImage());
           book.setImage(model.getImage());
       }
        this.bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = this.bookRepository.getOne(id);
        if (book.getImage()!=null) {
            this.cloudService.deleteImageByUrl(book.getImage());
        }
        this.bookRepository.deleteById(id);

    }

    private void prepareBookForDb(Book book, BookCreateEditServiceModel model) {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate releaseDate = LocalDate.parse(model.getReleaseDate(), formatter);

        book.setReleaseDate(releaseDate);

    }

}
