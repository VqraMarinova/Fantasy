package com.vyara.fantasy.data.entities.secondary;

import com.vyara.fantasy.data.entities.*;
import com.vyara.fantasy.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @Column(columnDefinition = "NVARCHAR(600)")
    private String content;

    @Column(name = "publish_date")
    private LocalDate publishDate;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private ShortStory story;

    @ManyToOne
    private Question question;



}
