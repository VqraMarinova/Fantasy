package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.ShortStoryCreateModel;
import com.vyara.fantasy.data.models.service.ShortStoryCreateServiceModel;
import com.vyara.fantasy.services.ShortStoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ShortStoryController {
    private final ModelMapper modelMapper;
    private final ShortStoryService shortStoryService;

    public ShortStoryController(ModelMapper modelMapper, ShortStoryService shortStoryService) {
        this.modelMapper = modelMapper;
        this.shortStoryService = shortStoryService;
    }


    @GetMapping("/add-story")
    public String getAddShortStoryForm() {
        return "addShortStory";
    }

    @PostMapping("/add-story")
    public String AddShortStory(@Valid @ModelAttribute ShortStoryCreateModel shortStoryCreateModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addShortStory";
        }
        shortStoryService.addNewShortStory(this.modelMapper.map(shortStoryCreateModel, ShortStoryCreateServiceModel.class));
        return "redirect:/";
    }

}
