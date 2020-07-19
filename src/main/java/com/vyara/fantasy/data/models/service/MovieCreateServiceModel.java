package com.vyara.fantasy.data.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateServiceModel {


    private String title;
    private String trailerLink;
    private String description;
    private String director;
    private String cast;
    private String releaseDate;

}
