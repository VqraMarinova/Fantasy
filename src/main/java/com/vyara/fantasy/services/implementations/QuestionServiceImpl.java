package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.models.ViewModels.AllQuestionsViewModel;
import com.vyara.fantasy.data.models.ViewModels.QuestionViewModel;
import com.vyara.fantasy.data.models.service.QuestionCreateEditServiceModel;
import com.vyara.fantasy.repositories.QuestionRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.CommentService;
import com.vyara.fantasy.services.QuestionService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final CommentService commentService;


    @Override
    public void addNewQuestion(QuestionCreateEditServiceModel questionCreateEditServiceModel) {

        Question question = this.modelMapper.map(questionCreateEditServiceModel, Question.class);

        question.setUser(this.authenticatedUserService.getCurrentUser());
        question.setPublishDate(LocalDate.now());

        this.questionRepository.save(question);

    }

    @Override
    public List<AllQuestionsViewModel> getAllQuestions(){
        List<AllQuestionsViewModel> allQuestions = new ArrayList<>();

        this.questionRepository.findAll().forEach(q->{
            AllQuestionsViewModel model = modelMapper.map(q, AllQuestionsViewModel.class);
            model.setPublishDate(q.getPublishDate().toString());
            model.setUser(q.getUser().getUsername());
            model.setAnswersCount(q.getAnswers().size());

            allQuestions.add(model);

        });
        return allQuestions;

    };
    @Override
    public QuestionViewModel getViewQuestion(Long id){
        Question question = this.questionRepository.getOne(id);
        QuestionViewModel model = this.modelMapper.map(question, QuestionViewModel.class);
        model.setPublishDate(question.getPublishDate().toString());
        model.setUser(question.getUser().getUsername());
        model.setAnswers(this.commentService.getCommentByQuestion(question));


        return model;
    }

    @Override
    public void deleteQuestion(Long id) {
        this.questionRepository.delete(this.questionRepository.getOne(id));

    }

    @Override
    public void editQuestion(QuestionCreateEditServiceModel model, Long id) {
        Question question = this.questionRepository.getOne(id);
        question.setTitle(model.getTitle());
        question.setContent(model.getContent());
        this.questionRepository.save(question);


    }

    @Override
    public List<AllQuestionsViewModel> getAllQuestionsForUser(){
        List<AllQuestionsViewModel> allQuestionsForUser = new ArrayList<>();

        this.questionRepository.getAllByUser(this.authenticatedUserService.getCurrentUser()).forEach(q->{
            AllQuestionsViewModel model = modelMapper.map(q, AllQuestionsViewModel.class);
            model.setPublishDate(q.getPublishDate().toString());
            model.setUser(q.getUser().getUsername());
            model.setAnswersCount(q.getAnswers().size());

            allQuestionsForUser.add(model);

        });
        return allQuestionsForUser;

    };
}

