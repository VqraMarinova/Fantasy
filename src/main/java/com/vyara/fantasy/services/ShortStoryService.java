package com.vyara.fantasy.services;

import com.vyara.fantasy.data.models.ViewModels.AllShortStoriesViewModel;
import com.vyara.fantasy.data.models.ViewModels.ShortStoryViewModel;
import com.vyara.fantasy.data.models.service.ShortStoryCreateEditServiceModel;

import java.util.List;

public interface ShortStoryService {
    void addNewShortStory(ShortStoryCreateEditServiceModel shortStoryCreateEditServiceModel);

    List<AllShortStoriesViewModel> getAllStories();

    ShortStoryViewModel getViewStory(Long id);

    void editShortStory(ShortStoryCreateEditServiceModel map, Long id);

    void deleteShortStory(Long id);

    List<AllShortStoriesViewModel> getAllStoriesForUser();
}
