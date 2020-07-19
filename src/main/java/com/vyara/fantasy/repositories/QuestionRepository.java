package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
