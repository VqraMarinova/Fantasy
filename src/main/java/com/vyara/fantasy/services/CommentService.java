package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.models.Binding.CommentCreateEditModel;
import com.vyara.fantasy.data.models.ViewModels.CommentViewModel;

import java.util.List;

public interface CommentService {

    void addCommentToBook(CommentCreateEditModel model, Long id);

    List<CommentViewModel> getCommentByBook(Book book);

    List<CommentViewModel> getCommentByMovie(Movie movie);

    List<CommentViewModel> getCommentByStory(ShortStory story);

    List<CommentViewModel> getCommentByQuestion(Question question);

    void addCommentToMovie(CommentCreateEditModel commentCreateEditModel, Long id);

    void addCommentToStory(CommentCreateEditModel commentCreateEditModel, Long id);

    void addCommentToQuestion(CommentCreateEditModel commentCreateEditModel, Long id);

    void editComment(CommentCreateEditModel commentCreateEditModel, Long id);

    void deleteComment(Long id);
}
