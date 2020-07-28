package com.vyara.fantasy.services.implementations;

import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.models.ViewModels.AllShortStoriesViewModel;
import com.vyara.fantasy.data.models.ViewModels.ShortStoryViewModel;
import com.vyara.fantasy.data.models.service.ShortStoryCreateEditServiceModel;
import com.vyara.fantasy.repositories.ShortStoryRepository;
import com.vyara.fantasy.services.AuthenticatedUserService;
import com.vyara.fantasy.services.CommentService;
import com.vyara.fantasy.services.RatingService;
import com.vyara.fantasy.services.ShortStoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShortStoryServiceImpl implements ShortStoryService {
    private final ModelMapper modelMapper;
    private final ShortStoryRepository shortStoryRepository;
    private final AuthenticatedUserService authenticatedUserService;
    private final RatingService ratingService;
    private final CommentService commentService;

    public ShortStoryServiceImpl(ModelMapper modelMapper, ShortStoryRepository shortStoryRepository, AuthenticatedUserService authenticatedUserService, RatingService ratingService, CommentService commentService) {
        this.modelMapper = modelMapper;
        this.shortStoryRepository = shortStoryRepository;
        this.authenticatedUserService = authenticatedUserService;
        this.ratingService = ratingService;
        this.commentService = commentService;
    }


    @Override
    public void addNewShortStory(ShortStoryCreateEditServiceModel shortStoryCreateEditServiceModel) {

        //TODO
        //To check if book already exists

        ShortStory shortStory = this.modelMapper.map(shortStoryCreateEditServiceModel, ShortStory.class);
        shortStory.setAddedOn(LocalDate.now());
        shortStory.setUser(this.authenticatedUserService.getCurrentUser());

        this.shortStoryRepository.save(shortStory);

    }

    @Override
    public List<AllShortStoriesViewModel> getAllStories(){
        List<AllShortStoriesViewModel> allStories = new ArrayList<>();

        this.shortStoryRepository.findAll().forEach(s->{
            AllShortStoriesViewModel model = modelMapper.map(s, AllShortStoriesViewModel.class);
            model.setUser(s.getUser().getUsername());
            model.setRating(this.ratingService.getRatingByStory(s));
            allStories.add(model);

        });
        return allStories;

    };
    @Override
    public ShortStoryViewModel getViewStory(Long id){
        ShortStory story = this.shortStoryRepository.getOne(id);
        ShortStoryViewModel model = this.modelMapper.map(story, ShortStoryViewModel.class);

        model.setRating(this.ratingService.getRatingByStory(story));
        model.setComments(this.commentService.getCommentByStory(story));
        model.setAddedOn(story.getAddedOn().toString());
        model.setUser(story.getUser().getUsername());



        return model;
    }

    @Override
    public void editShortStory(ShortStoryCreateEditServiceModel model, Long id) {
        ShortStory shortStory = this.shortStoryRepository.getOne(id);
        shortStory.setTitle(model.getTitle());
        shortStory.setContent(model.getContent());
        this.shortStoryRepository.save(shortStory);

    }

    @Override
    public void deleteShortStory(Long id) {
        this.shortStoryRepository.deleteById(id);

    }
}

