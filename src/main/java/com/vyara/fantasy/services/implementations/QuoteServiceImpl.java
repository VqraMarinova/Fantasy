package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.config.EntityAlreadyExistsException;
import com.vyara.fantasy.data.entities.SmartQuote;
import com.vyara.fantasy.data.models.ViewModels.QuoteReturnModel;
import com.vyara.fantasy.data.models.service.QuoteCreateEditServiceModel;
import com.vyara.fantasy.repositories.QuoteRepository;
import com.vyara.fantasy.services.QuoteService;
import com.vyara.fantasy.services.validation.EntityValidator;
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
    private final EntityValidator entityValidator;

    @Override
    public void addNewQuote(QuoteCreateEditServiceModel model) {
        if (!this.entityValidator.isQuoteValid(model)){
            throw new EntityAlreadyExistsException("Quote already exists");
        }
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

    @Override
    public void chooseUpQuoteOfTheDay() {
        long leftLimit = 0L;
        long rightLimit = (this.quoteRepository.count());
        long index = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
        this.quoteRepository
                .findAll().forEach(q->{
            q.setQuoteOfTheDay(false);
            this.quoteRepository.saveAndFlush(q);
        });
        SmartQuote smartQuote = this.quoteRepository.getOne(index);
        smartQuote.setQuoteOfTheDay(true);
        this.quoteRepository.saveAndFlush(smartQuote);

    }

    @Override
    public QuoteReturnModel getQuoteOfTheDay(){
       return this.modelMapper.map(this.quoteRepository.getByQuoteOfTheDayIsTrue(), QuoteReturnModel.class);

    }


}
