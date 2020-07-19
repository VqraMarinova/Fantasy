package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.models.service.ShortStoryCreateServiceModel;
import com.vyara.fantasy.repositories.ShortStoryRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.ShortStoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ShortStoryServiceImpl implements ShortStoryService {
    private final ModelMapper modelMapper;
    private final ShortStoryRepository shortStoryRepository;
    private final AuthenticatedUserService authenticatedUserService;

    public ShortStoryServiceImpl(ModelMapper modelMapper, ShortStoryRepository shortStoryRepository, AuthenticatedUserService authenticatedUserService) {
        this.modelMapper = modelMapper;
        this.shortStoryRepository = shortStoryRepository;
        this.authenticatedUserService = authenticatedUserService;
    }


    @Override
    public void addNewShortStory(ShortStoryCreateServiceModel shortStoryCreateServiceModel) {

        //TODO
        //To check if book already exists

        ShortStory shortStory = this.modelMapper.map(shortStoryCreateServiceModel, ShortStory.class);
        shortStory.setAddedOn(LocalDate.now());
        shortStory.setUser(this.authenticatedUserService.getCurrentUser());

        this.shortStoryRepository.save(shortStory);

    }
}

