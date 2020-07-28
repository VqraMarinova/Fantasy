package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.QuoteReturnModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;

import java.util.List;

public interface QuoteService {
    void addNewQuote(QuoteCreateEditServiceModel model);

    void editQuote(QuoteCreateEditServiceModel model, Long id);

    void deleteQuote(Long id);

    List<QuoteReturnModel> getAllQuotes();
}
