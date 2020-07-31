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

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Author name should have only letters and space")
    private String author;

    @NotNull
    @Size(min=10, max=400)
    private String content;

}
