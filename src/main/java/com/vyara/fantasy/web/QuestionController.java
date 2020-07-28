package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.QuestionCreateEditModel;
import com.vyara.fantasy.data.models.service.QuestionCreateEditServiceModel;
import com.vyara.fantasy.services.QuestionService;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String AddQuestion(@Valid @ModelAttribute QuestionCreateEditModel questionCreateEditModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addQuestion";
        }
        questionService.addNewQuestion(this.modelMapper.map(questionCreateEditModel, QuestionCreateEditServiceModel.class));
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/question/{id}")
    public String editQuestion(@Valid @ModelAttribute QuestionCreateEditModel questionCreateEditModel, @PathVariable Long id ){
        this.questionService.editQuestion(this.modelMapper.map(
                questionCreateEditModel, QuestionCreateEditServiceModel.class
        ), id);
        return String.format("redirect:/explore/question/%s",id);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/question/{id}")
    public String deleteQuestion(@PathVariable Long id ) {
        this.questionService.deleteQuestion(id);
        return "redirect:/explore/questions";
    }

}
