package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterModel {

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{3,15}$", message = "Username should have between 3 and 15 characters. Valid characters: a-z, A-Z, 0-9, points, dashes and underscores")
    private String username;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Full name should have only letters and space")
    private String fullName;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Please enter valid email")
    private String email;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,25})", message = "Should be between 5 and 25 characters and contain at least: one lower and one uppercase letter and one digit.")
    private String password;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{5,25})")
    private String confirmPassword;
}
