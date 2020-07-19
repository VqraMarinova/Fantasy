package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.service.ChangeEmailServiceModel;
import com.vyara.fantasy.data.models.service.ChangePasswordServiceModel;
import com.vyara.fantasy.data.models.service.UserRegisterServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void register(UserRegisterServiceModel userRegisterServiceModel);

    void changePassword(ChangePasswordServiceModel changePasswordServiceModel) throws Exception;

    void changeEmail(ChangeEmailServiceModel changeEmailServiceModel) throws Exception;
}
