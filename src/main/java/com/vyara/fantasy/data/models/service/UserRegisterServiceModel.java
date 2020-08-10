package com.vyara.fantasy.data.models.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterServiceModel {

    private String username;
    private String email;
    private String password;
    private String confirmPassword;
}
