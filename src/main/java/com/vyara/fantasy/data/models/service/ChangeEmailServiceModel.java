package com.vyara.fantasy.data.models.service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeEmailServiceModel {

    private String newEmail;
    private String confirmEmail;
}
