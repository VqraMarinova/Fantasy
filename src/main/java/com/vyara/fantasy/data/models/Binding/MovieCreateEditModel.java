package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateEditModel {


    @NotNull
    @Size(min=3, max=30)
    private String title;

    @Pattern(regexp = "^(http(s)??\\:\\/\\/)?(www\\.)?((youtube\\.com\\/watch\\?v=)|(youtu.be\\/))([a-zA-Z0-9\\-_])+$", message = "Only youtube videos are allowed currently")
    private String trailerLink;

    @NotNull
    @Size(min=10, max=600)
    private String description;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Director name should have only letters and space")
    private String director;

    @Pattern(regexp = "^[A-Za-z\\s]+(?:,[A-Za-z\\s]*)*$", message = "Names should be separate by coma")
    @Size(min=5, max=250)
    private String cast;

    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Please enter date in valid period")
    private String releaseDate;

}
