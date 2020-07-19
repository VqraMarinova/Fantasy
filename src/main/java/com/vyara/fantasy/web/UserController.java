package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.UserRegisterModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import com.vyara.fantasy.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/register")
    public String getRegisterForm() {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginForm(){return "login";}

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute UserRegisterModel user, AbstractBindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(this.modelMapper.map(user, UserRegisterServiceModel.class));
        return "redirect:/";
    }


}
