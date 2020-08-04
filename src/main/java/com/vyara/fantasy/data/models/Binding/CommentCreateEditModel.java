package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CommentCreateEditModel {

    @NotNull(message = "Field can not be empty")
    @Size(min=10, max=600, message = "Please enter enter between 10 and 600 characters")
    private String content;

}
