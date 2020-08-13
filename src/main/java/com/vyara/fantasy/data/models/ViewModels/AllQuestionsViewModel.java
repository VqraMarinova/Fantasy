package com.vyara.fantasy.data.models.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllQuestionsViewModel {
    private Long id;
    private String title;
    private String publishDate;
    private String user;
    private int answersCount;
    private Boolean newAnswers;
}
