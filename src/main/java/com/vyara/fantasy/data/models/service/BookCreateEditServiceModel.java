package com.vyara.fantasy.data.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateEditServiceModel {

    private String title;
    private String image;
    private String description;
    private String author;
    private String releaseDate;

}
