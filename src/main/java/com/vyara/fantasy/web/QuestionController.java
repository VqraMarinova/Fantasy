package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.QuestionCreateModel;
import com.vyara.fantasy.data.models.service.QuestionCreateServiceModel;
import com.vyara.fantasy.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class QuestionController {
    private final ModelMapper modelMapper;
    private final QuestionService questionService;

    public QuestionController(ModelMapper modelMapper, QuestionService questionService) {
        this.modelMapper = modelMapper;
        this.questionService = questionService;
    }


    @GetMapping("/add-question")
    public String getAddQuestionForm() {
        return "addQuestion";
    }

    @PostMapping("/add-question")
    public String AddQuestion(@Valid @ModelAttribute QuestionCreateModel questionCreateModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addQuestion";
        }
        questionService.addNewQuestion(this.modelMapper.map(questionCreateModel, QuestionCreateServiceModel.class));
        return "redirect:/";
    }

}
