package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.secondary.Author;
import com.vyara.fantasy.repositories.AuthorRepository;
import com.vyara.fantasy.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @Override
    public Author findAuthorByName(String name) {
        try {
            return authorRepository.getByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Author createAuthor(String name) {
        Author author = new Author();
        author.setName(name);
        this.authorRepository.save(author);
        return author;
    }
}
