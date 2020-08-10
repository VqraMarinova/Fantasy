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
public class BookViewModel {

    private Long id;
    private String title;
    private String image;
    private String description;
    private String releaseDate;
    private String author;
    private List<CommentViewModel> comments;
    private String rating;
    private Boolean canVote;


}
