package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateModel {

    private String title;
    private String image;
    private String description;
    private String author;
    private String releaseDate;

}
