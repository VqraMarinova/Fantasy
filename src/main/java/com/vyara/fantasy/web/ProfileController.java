package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.ChangeEmailModel;
import com.vyara.fantasy.data.models.Binding.ChangePasswordModel;
import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class ProfileController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ProfileController(ModelMapper modelMapper, UserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String getRegisterForm() {
        return "profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute ChangePasswordModel changePasswordModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
           throw new Exception();
        }
        this.userService.changePassword(this.modelMapper.map(changePasswordModel, ChangePasswordServiceModel.class));
        return "profile";
    }


    @PostMapping("/changeEmail")
    public String changeEmail(@Valid @ModelAttribute ChangeEmailModel changeEmailModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception();
        }
        this.userService.changeEmail(this.modelMapper.map(changeEmailModel, ChangeEmailServiceModel.class));
        return "profile";
    }

}
