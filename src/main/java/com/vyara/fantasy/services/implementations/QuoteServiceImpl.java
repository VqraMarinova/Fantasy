package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.SmartQuote;
import com.vyara.fantasy.data.models.ViewModels.QuoteReturnModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.repositories.QuoteRepository;
import com.vyara.fantasy.services.QuoteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class QuoteServiceImpl implements QuoteService {
    private final ModelMapper modelMapper;
    private final QuoteRepository quoteRepository;

    @Override
    public void addNewQuote(QuoteCreateEditServiceModel model) {
        this.quoteRepository.save(this.modelMapper.map(model, SmartQuote.class));
    }

    @Override
    public void editQuote(QuoteCreateEditServiceModel model, Long id) {
        SmartQuote quote = this.quoteRepository.getOne(id);
        quote.setAuthor(model.getAuthor());
        quote.setContent(model.getContent());
        this.quoteRepository.save(quote);
    }

    @Override
    public void deleteQuote(Long id) {
        this.quoteRepository.deleteById(id);

    }

    @Override
    public List<QuoteReturnModel> getAllQuotes(){
        List<QuoteReturnModel> models = new ArrayList<>();
        this.quoteRepository.findAll().forEach(q ->{
            models.add(this.modelMapper.map(q, QuoteReturnModel.class));
        });

        return models;
    }
}
