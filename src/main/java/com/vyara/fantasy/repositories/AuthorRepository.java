package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.secondary.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getByName(String name);
}
