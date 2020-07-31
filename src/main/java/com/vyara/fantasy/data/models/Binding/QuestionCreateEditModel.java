package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class QuestionCreateEditModel {


    @NotNull
    @Size(min=3, max=30)
    private String title;

    @NotNull
    @Size(min=10, max=800)
    private String content;


}
