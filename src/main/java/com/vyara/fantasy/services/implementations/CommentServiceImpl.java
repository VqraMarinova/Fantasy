package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.secondary.Comment;
import com.vyara.fantasy.data.models.Binding.CommentCreateEditModel;
import com.vyara.fantasy.data.models.ViewModels.CommentViewModel;
import com.vyara.fantasy.repositories.*;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;
    private final AuthenticatedUserService authenticatedUserService;
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;
    private final ShortStoryRepository shortStoryRepository;
    private final QuestionRepository questionRepository;


    @Override
    public void addCommentToBook(CommentCreateEditModel model, Long id){
        Comment comment = prepareCommentForDB(model);
        comment.setBook(this.bookRepository.getOne(id));
        this.commentRepository.save(comment);
    }

    @Override
    public void addCommentToMovie(CommentCreateEditModel model, Long id) {
        Comment comment = prepareCommentForDB(model);
        comment.setMovie(this.movieRepository.getOne(id));
        this.commentRepository.save(comment);
    }

    @Override
    public void addCommentToStory(CommentCreateEditModel model, Long id) {
        Comment comment = prepareCommentForDB(model);
        ShortStory story = this.shortStoryRepository.getOne(id);
        comment.setStory(story);
        this.commentRepository.save(comment);
        if (!comment.getUser().getUsername().equals(story.getUser().getUsername())) {
            story.setNewAnswers(true);
            this.shortStoryRepository.save(story);
        }
    }

    @Override
    public void addCommentToQuestion(CommentCreateEditModel model, Long id) {
        Comment comment = prepareCommentForDB(model);
        Question question = this.questionRepository.getOne(id);
        comment.setQuestion(question);
        this.commentRepository.save(comment);
        if (!comment.getUser().getUsername().equals(question.getUser().getUsername())) {
            question.setNewAnswers(true);
            this.questionRepository.save(question);
        }
    }

    @Override
    public void editComment(CommentCreateEditModel model, Long id) {
        Comment comment = this.commentRepository.getOne(id);
        comment.setContent(model.getContent());
        this.commentRepository.save(comment);

    }

    @Override
    public void deleteComment(Long id) {
        this.commentRepository.delete(this.commentRepository.getOne(id));

    }


    @Override
    public List<CommentViewModel> getCommentByBook(Book book){
        List <CommentViewModel> comments = new ArrayList<>();
        this.commentRepository.findAllByBook(book)
                .forEach(c->{
                    comments.add(mapComment(c));
                });

        return comments;
    }

    @Override
    public List<CommentViewModel> getCommentByMovie(Movie movie){
        List <CommentViewModel> comments = new ArrayList<>();
        this.commentRepository.findAllByMovie(movie)
                .forEach(c->{
                    comments.add(mapComment(c));
                });

        return comments;
    }



    @Override
    public List<CommentViewModel> getCommentByStory(ShortStory story){
        List <CommentViewModel> comments = new ArrayList<>();
        this.commentRepository.findAllByStory(story)
                .forEach(c->{
                    comments.add(mapComment(c));
                });
        return comments;
    }

    @Override
    public List<CommentViewModel> getCommentByQuestion(Question question){
        List <CommentViewModel> comments = new ArrayList<>();
        this.commentRepository.findAllByQuestion(question)
                .forEach(c->{
                    comments.add(mapComment(c));
                });
        return comments;
    }


    private CommentViewModel mapComment(Comment comment) {
        CommentViewModel model = this.modelMapper.map(comment, CommentViewModel.class);
        model.setUser(comment.getUser().getUsername());
        model.setPublishDate(comment.getPublishDate().toString());
        return model;
    }

    private Comment prepareCommentForDB(CommentCreateEditModel model) {
        Comment comment = new Comment();
        comment.setContent(model.getContent());
        comment.setUser(this.authenticatedUserService.getCurrentUser());
        comment.setPublishDate(LocalDate.now());
        return comment;
    }

}
