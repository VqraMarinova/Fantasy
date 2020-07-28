package com.vyara.fantasy.data.models.ViewModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllMoviesViewModel {
    private Long id;
    private String title;
    private String cast;
    private String rating;

}
