package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.AllUsersModel;

import java.util.List;

public interface AdminService {

    List<AllUsersModel> getAllUsersForRole(String searchRole, String excludeRole);

    void removeRole(String role, String userName);

    void addRole(String role, String userName);
}
