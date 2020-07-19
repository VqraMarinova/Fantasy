package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.secondary.Role;
import com.vyara.fantasy.repositories.RoleRepository;
import com.vyara.fantasy.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void seedRolesInDb() {
        this.roleRepository.saveAndFlush(new Role("ROOT"));
        this.roleRepository.saveAndFlush(new Role("ADMIN"));
        this.roleRepository.saveAndFlush(new Role("MODERATOR"));
        this.roleRepository.saveAndFlush(new Role("USER"));
    }

    @Override
    public Set<Role> getAllRoles() {
        return new HashSet<>(this.roleRepository.findAll());
    }

    @Override
    public Role findByRoleName(String role) {
        return this.roleRepository.getByAuthority(role);
    }
}
