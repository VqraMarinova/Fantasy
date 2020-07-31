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

    @NotNull
    @Size(min=3, max=30)
    private String title;

    @NotNull
    @Size(min=10, max=600)
    private String summary;

    @NotNull
    @Size(min=500, max=7000)
    private String content;

}
