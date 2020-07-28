package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.AllUsersViewModel;

import javax.naming.AuthenticationException;
import java.util.List;

public interface AdminService {

    List<AllUsersViewModel> getAllUsersForRole(String searchRole, String excludeRole);

    void removeRole(String role, String userName) throws AuthenticationException;

    void addRole(String role, String userName);
}
