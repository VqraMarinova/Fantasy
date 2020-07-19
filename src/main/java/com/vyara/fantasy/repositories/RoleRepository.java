package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.secondary.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getByAuthority(String authority);
}
