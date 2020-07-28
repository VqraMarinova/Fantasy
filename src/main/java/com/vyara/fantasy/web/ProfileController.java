package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.ChangeEmailModel;
import com.vyara.fantasy.data.models.Binding.ChangePasswordModel;
import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class ProfileController {
    private final ModelMapper modelMapper;
    private final UserService userService;

    @ModelAttribute("emailModel")
    public ChangeEmailModel emailModel (){
        return new ChangeEmailModel();
    }

    @ModelAttribute("passwordModel")
    public ChangePasswordModel passwordModel (){
        return new ChangePasswordModel();
    }

    @GetMapping("/profile")
    public String getRegisterForm(@ModelAttribute("emailModel") ChangeEmailModel emailModel, @ModelAttribute("passwordModel") ChangePasswordModel passwordModel) {
        return "profile";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute("passwordModel") ChangePasswordModel passwordModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
           throw new Exception();
        }
        this.userService.changePassword(this.modelMapper.map(passwordModel, ChangePasswordServiceModel.class));
        return "profile";
    }


    @PostMapping("/changeEmail")
    public String changeEmail(@Valid @ModelAttribute("emailModel") ChangeEmailModel emailModel, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new Exception();
        }
        this.userService.changeEmail(this.modelMapper.map(emailModel, ChangeEmailServiceModel.class));
        return "profile";
    }

}
