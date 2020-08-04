package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShortStoryRepository extends JpaRepository<ShortStory, Long> {
    List<ShortStory> getAllByUser(User user);

}
