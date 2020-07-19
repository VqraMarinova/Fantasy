package com.vyara.fantasy.repositories;

import com.vyara.fantasy.data.entities.secondary.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
