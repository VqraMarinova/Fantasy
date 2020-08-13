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
    @Pattern(regexp = "^[a-zA-Z0-9._-]{5,20}$", message = "Username should have between 5 and 20 characters. Valid characters: a-z, A-Z, 0-9, points, dashes and underscores")
    private String username;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$", message = "Please enter valid email")
    private String email;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,60})", message = "Password Should have min 8 characters,it should contain at least: one lower and one uppercase letter one digit and one special character.")
    private String password;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,60})")
    private String confirmPassword;
}
