package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.User;

import java.util.List;

public interface AuthenticatedUserService {
    User getCurrentUser();

    String getUsername();

    List<String> getRoles();


    void loginAfterRegister(User user);
}

