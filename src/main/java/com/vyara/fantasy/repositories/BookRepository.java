package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
boolean existsByAuthorAndTitle(String author, String title);

}
