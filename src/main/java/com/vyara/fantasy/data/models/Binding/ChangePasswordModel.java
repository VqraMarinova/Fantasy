package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordModel {

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,25})")
    private String currentPassword;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,25})", message = "Should be between 5 and 25 characters and contain at least: one lower and one uppercase letter and one digit.")
    private String newPassword;

    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,25})", message = "Should be between 5 and 25 characters and contain at least: one lower and one uppercase letter and one digit.")
    private String confirmPassword;
}
