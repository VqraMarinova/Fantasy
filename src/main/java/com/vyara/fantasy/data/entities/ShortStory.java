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
@Table(name = "short_stories")
public class ShortStory extends BaseEntity {

    @Column
    private String title;

    @Column
    private String summary;

    @Column
    private String content;

    @Column(name = "release_date")
    private LocalDate addedOn;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "story")
    private List<Comment> comments;

    @OneToMany(mappedBy = "story")
    private List<Rating> rating;

}
