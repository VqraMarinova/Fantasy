package com.vyara.fantasy.data.models.ViewModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class QuoteReturnModel {

    private Long id;
    private String author;
    private String content;

}
