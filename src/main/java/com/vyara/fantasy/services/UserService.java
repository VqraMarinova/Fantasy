package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.CheckUserViewModel;
import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    void register(UserRegisterServiceModel userRegisterServiceModel) throws Exception;

    void changePassword(ChangePasswordServiceModel changePasswordServiceModel) throws Exception;

    void changeEmail(ChangeEmailServiceModel changeEmailServiceModel) throws Exception;

    List<CheckUserViewModel> getCheckUsers();
}
