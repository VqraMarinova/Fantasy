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
public class QuestionViewModel {

    private Long id;
    private String title;
    private String content;
    private String publishDate;
    private String user;
    private List<CommentViewModel> answers;


}
