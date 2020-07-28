package com.vyara.fantasy.data.entities;

import com.vyara.fantasy.data.entities.base.BaseEntity;
import com.vyara.fantasy.data.entities.secondary.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column
    private String title;

    @Column(columnDefinition = "NVARCHAR(800)")
    private String content;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @ManyToOne()
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Comment> answers;

}
