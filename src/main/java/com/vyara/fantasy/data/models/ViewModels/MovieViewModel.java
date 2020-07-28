package com.vyara.fantasy.data.models.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MovieViewModel {

    private Long id;
    private String title;
    private String trailerLink;
    private String director;
    private String cast;
    private String releaseDate;
    private String description;
    private List<CommentViewModel> comments;
    private String rating;

}
