package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Question;
import com.vyara.fantasy.data.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> getAllByUser(User user);

}
