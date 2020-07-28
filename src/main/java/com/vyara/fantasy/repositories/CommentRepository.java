package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.secondary.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByBook(Book book);
    List<Comment> findAllByMovie(Movie movie);
    List<Comment> findAllByStory(ShortStory story);
    List<Comment> findAllByQuestion(Question question);
}
