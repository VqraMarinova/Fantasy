package com.vyara.fantasy.web;

import com.vyara.fantasy.data.models.Binding.UserRegisterModel;
import com.vyara.fantasy.data.models.ViewModels.CheckUserResponseModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import com.vyara.fantasy.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.AbstractBindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;


    @ModelAttribute("model")
    public UserRegisterModel model (){
        return new UserRegisterModel();
    }

    @GetMapping("/register")
    public String getRegisterForm(@ModelAttribute("model") UserRegisterModel model) {
        return "register";
    }

    @GetMapping("/login")
    public String getLoginForm(){return "login";}

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("model") UserRegisterModel model, AbstractBindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.register(this.modelMapper.map(model, UserRegisterServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/api/check-users-validity")
    @ResponseBody
    public List<CheckUserResponseModel> getUsersValidity(){
       return this.userService.getCheckUsers();
    }


}
