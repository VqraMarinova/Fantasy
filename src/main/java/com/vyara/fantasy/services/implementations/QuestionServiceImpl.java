package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.models.service.QuestionCreateServiceModel;
import com.vyara.fantasy.repositories.QuestionRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final ModelMapper modelMapper;
    private final QuestionRepository questionRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public QuestionServiceImpl(ModelMapper modelMapper, QuestionRepository questionRepository, AuthenticatedUserService authenticatedUserService) {
        this.modelMapper = modelMapper;
        this.questionRepository = questionRepository;
        this.authenticatedUserService = authenticatedUserService;
    }


    @Override
    public void addNewQuestion(QuestionCreateServiceModel questionCreateServiceModel) {

        //TODO
        //To check if book already exists

        Question question = this.modelMapper.map(questionCreateServiceModel, Question.class);

        question.setUser(this.authenticatedUserService.getCurrentUser());

        this.questionRepository.save(question);

    }
}

