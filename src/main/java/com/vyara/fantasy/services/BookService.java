package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.AllBooksViewModel;
import com.vyara.fantasy.data.models.ViewModels.BookViewModel;
import com.vyara.fantasy.data.models.service.BookCreateEditServiceModel;

import java.util.List;

public interface BookService {
    void addNewBook(BookCreateEditServiceModel bookCreateEditServiceModel) throws Exception;

    List<AllBooksViewModel> getAllBooks();

    BookViewModel getViewBook(Long id);

    void editBook(BookCreateEditServiceModel map, Long id);

    void deleteBook(Long id);
}
