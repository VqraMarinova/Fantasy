package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ShortStoryCreateEditModel {

    @NotNull(message = "Field can not be empty")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String title;

    @NotNull(message = "Field can not be empty")
    @Size(min=30, max=600, message = "Please enter enter between 30 and 600 characters")
    private String summary;

    @NotNull(message = "Field can not be empty")
    @Size(min=400, max=7000, message = "Please enter enter between 400 and 7000 characters")
    private String content;

}
