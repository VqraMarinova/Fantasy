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
public class QuoteCreateEditModel {

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Author name should have only letters and space")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String author;

    @NotNull(message = "Field can not be empty")
    @Size(min=30, max=400, message = "Please enter enter between 30 and 400 characters")
    private String content;

}
