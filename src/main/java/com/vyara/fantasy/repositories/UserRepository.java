package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.entities.secondary.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User getByUsername(String username);
    List<User> getAllByAuthorities(Role searchRole);


    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}