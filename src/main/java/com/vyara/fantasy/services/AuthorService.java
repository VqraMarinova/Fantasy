package com.vyara.fantasy.services;

import com.vyara.fantasy.data.entities.secondary.Author;

public interface AuthorService {
    Author findAuthorByName(String name);
    Author createAuthor(String name);

}
