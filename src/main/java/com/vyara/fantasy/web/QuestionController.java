package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.QuestionCreateEditModel;
import com.vyara.fantasy.data.models.service.QuestionCreateEditServiceModel;
import com.vyara.fantasy.services.QuestionService;
import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class QuestionController {
    private final ModelMapper modelMapper;
    private final QuestionService questionService;

    @ModelAttribute("questionModel")
    public QuestionCreateEditModel questionModel (){
        return new QuestionCreateEditModel();
    }


    @GetMapping("/add-question")
    public String getAddQuestionForm(@ModelAttribute("questionModel") QuestionCreateEditModel questionModel) {
        return "addQuestion";
    }

    @PostMapping("/add-question")
    public String AddQuestion(@Valid @ModelAttribute("questionModel") QuestionCreateEditModel questionModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addQuestion";
        }
        questionService.addNewQuestion(this.modelMapper.map(questionModel, QuestionCreateEditServiceModel.class));
        return "redirect:/explore/questions";
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/question/{id}")
    public String editQuestion(@Valid @ModelAttribute("model") QuestionCreateEditModel questionModel, @PathVariable Long id, AbstractBindingResult bindingResult ){
        if (bindingResult.hasErrors()) {
            return String.format("redirect:/edit/question/%s",id);
        }
        this.questionService.editQuestion(this.modelMapper.map(
                questionModel, QuestionCreateEditServiceModel.class
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
