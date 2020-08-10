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
public class ShortStoryViewModel {

    private Long id;
    private String title;
    private String summary;
    private String content;
    private String addedOn;
    private String user;
    private List<CommentViewModel> comments;
    private String rating;
    private Boolean canVote;

}
