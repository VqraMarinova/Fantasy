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
@Table(name = "books")
public class Book extends BaseEntity {

    @Column
    private String title;

    @Column
    private String image;

    @Column(columnDefinition = "NVARCHAR(600)")
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column
    private String author;

    @OneToMany(mappedBy = "book", cascade = CascadeType.REMOVE)
    private List<Comment> comments;

    @OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
    private List<Rating> rating;
}
