package com.vyara.fantasy.data.entities.secondary;

import com.vyara.fantasy.data.entities.Book;
import com.vyara.fantasy.data.entities.Movie;
import com.vyara.fantasy.data.entities.ShortStory;
import com.vyara.fantasy.data.entities.User;
import com.vyara.fantasy.data.entities.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "rating")
public class Rating extends BaseEntity {

    @Column
    private Integer rating;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Movie movie;

    @ManyToOne
    private ShortStory story;

    @ManyToOne(fetch= FetchType.EAGER)
    private User user;
}
