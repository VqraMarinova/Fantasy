package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.ShortStoryCreateEditModel;
import com.vyara.fantasy.data.models.service.ShortStoryCreateEditServiceModel;
import com.vyara.fantasy.services.ShortStoryService;
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
public class ShortStoryController {
    private final ModelMapper modelMapper;
    private final ShortStoryService shortStoryService;

    @ModelAttribute("storyModel")
    public ShortStoryCreateEditModel storyModel (){
        return new ShortStoryCreateEditModel();
    }

    @GetMapping("/add-story")
    public String getAddShortStoryForm(@ModelAttribute("storyModel") ShortStoryCreateEditModel storyModel) {
        return "addShortStory";
    }

    @PostMapping("/add-story")
    public String AddShortStory(@Valid @ModelAttribute("storyModel") ShortStoryCreateEditModel storyModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "addShortStory";
        }
        shortStoryService.addNewShortStory(this.modelMapper.map(storyModel, ShortStoryCreateEditServiceModel.class));
        return "redirect:/home";
    }

    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/edit/story/{id}")
    public String editShortStory(@Valid @ModelAttribute("storyModel") ShortStoryCreateEditModel storyModel, @PathVariable Long id ){
        this.shortStoryService.editShortStory(this.modelMapper.map(
                storyModel, ShortStoryCreateEditServiceModel.class
        ), id);
        return String.format("redirect:/explore/story/%s",id);
    }
    @PreAuthorize("hasAuthority('MODERATOR')")
    @PostMapping("/delete/story/{id}")
    public String deleteShortStory(@PathVariable Long id ) {
        this.shortStoryService.deleteShortStory(id);
        return "redirect:/explore/stories";
    }

}
