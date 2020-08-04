package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.ViewModels.*;
import com.vyara.fantasy.services.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class ExploreRestController {
    private final BookService bookService;
    private final MovieService movieService;
    private final ShortStoryService shortStoryService;
    private final QuestionService questionService;
    private final QuoteService quoteService;



    @GetMapping(value = "/api/explore/books")
    public List<AllBooksViewModel> getBooks() {
        return this.bookService.getAllBooks();

    }

    @GetMapping(value = "/api/explore/book/{id}")
    public BookViewModel getBook(@PathVariable Long id) {
        return this.bookService.getViewBook(id);

    }

    @GetMapping(value = "/api/explore/movies")
    public List<AllMoviesViewModel> getMovies() {
        return this.movieService.getAllMovies();

    }

    @GetMapping(value = "/api/explore/movie/{id}")
    public MovieViewModel getMovie(@PathVariable Long id) {
        return this.movieService.getViewMovie(id);

    }

    @GetMapping(value = "/api/explore/stories")
    public List<AllShortStoriesViewModel> getStories() {
        return this.shortStoryService.getAllStories();

    }

    @GetMapping(value = "/api/explore/story/{id}")
    public ShortStoryViewModel getStory(@PathVariable Long id) {
        return this.shortStoryService.getViewStory(id);

    }

    @GetMapping(value = "/api/explore/questions")
    public List<AllQuestionsViewModel> getQuestions() {
        return this.questionService.getAllQuestions();

    }

    @GetMapping(value = "/api/explore/question/{id}")
    public QuestionViewModel getQuestion(@PathVariable Long id) {
        return this.questionService.getViewQuestion(id);

    }

    @GetMapping(value = "/api/explore/quotes")
    public List<QuoteViewModel> getQuotes() {
        return this.quoteService.getAllQuotes();

    }


}
