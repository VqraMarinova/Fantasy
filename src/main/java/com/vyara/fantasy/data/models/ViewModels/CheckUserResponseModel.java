package com.vyara.fantasy.data.models.ViewModels;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckUserResponseModel {
    private String username;
    private String email;
}
