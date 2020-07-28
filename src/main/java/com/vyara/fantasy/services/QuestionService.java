package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.AllQuestionsViewModel;
import com.vyara.fantasy.data.models.ViewModels.QuestionViewModel;
import com.vyara.fantasy.data.models.service.QuestionCreateEditServiceModel;

import java.util.List;

public interface QuestionService {
    void addNewQuestion(QuestionCreateEditServiceModel questionStoryCreateServiceModel);

    List<AllQuestionsViewModel> getAllQuestions();

    QuestionViewModel getViewQuestion(Long id);

    void deleteQuestion(Long id);

    void editQuestion(QuestionCreateEditServiceModel map, Long id);
}
