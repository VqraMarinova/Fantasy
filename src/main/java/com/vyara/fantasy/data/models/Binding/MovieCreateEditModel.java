package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class MovieCreateEditModel {


    @NotNull(message = "Field can not be empty")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String title;

    @Nullable
    @Pattern(regexp = "(http(?:s?)://(?:www\\.)?youtu(?:be\\.com/(?:watch\\?v=|embed/)|\\.be/)([\\w\\-_]*))*", message = "Only youtube videos are allowed")
    private String trailerLink;

    @NotNull(message = "Field can not be empty")
    @Size(min=30, max=600, message = "Please enter enter between 30 and 600 characters")
    private String description;

    @NotNull(message = "Field can not be empty")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String director;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^[A-Za-z\\s]+(?:,[A-Za-z\\s]*)*$", message = "Names should be separate by coma")
    @Size(min=5, max=250, message = "Please enter enter between 5 and 250 characters")
    private String cast;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Please enter date in valid period")
    private String releaseDate;

}
