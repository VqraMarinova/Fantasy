package com.vyara.fantasy.data.entities;

import com.vyara.fantasy.data.entities.base.BaseEntity;
import com.vyara.fantasy.data.entities.secondary.Comment;
import com.vyara.fantasy.data.entities.secondary.Rating;
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
@Table(name = "movies")
public class Movie extends BaseEntity {

    @Column
    private String title;

    @Column(name = "trailer_link")
    private String trailerLink;

    @Column(columnDefinition = "NVARCHAR(600)")
    private String description;

    @Column
    private String director;

    @Column
    private String cast;

    @Column(name = "release_date")
    private LocalDate releaseDate;


    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.REMOVE)
    private List<Rating> rating;
}
