package com.vyara.fantasy.data.models.Binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ChangePasswordModel {


    @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,60})", message = "Password Should have min 8 characters,it should contain at least: one lower and one uppercase letter one digit and one special character.")
    private String newPassword;

    @Pattern(regexp = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{8,60})", message = "Password Should have min 8 characters,it should contain at least: one lower and one uppercase letter one digit and one special character.")
    private String confirmPassword;
}
