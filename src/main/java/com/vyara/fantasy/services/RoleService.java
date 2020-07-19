package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.secondary.Role;

import java.util.Set;

public interface RoleService {
    void seedRolesInDb();
    Set<Role> getAllRoles();
    Role findByRoleName(String role);
}
