package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.service.BookCreateServiceModel;

public interface BookService {
    void addNewBook(BookCreateServiceModel bookCreateServiceModel) throws Exception;
}
