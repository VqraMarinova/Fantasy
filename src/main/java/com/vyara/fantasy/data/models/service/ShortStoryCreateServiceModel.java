package com.vyara.fantasy.data.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShortStoryCreateServiceModel {

    private String title;
    private String summary;
    private String content;

}