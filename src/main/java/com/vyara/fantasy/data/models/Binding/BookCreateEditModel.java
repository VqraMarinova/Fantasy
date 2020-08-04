package com.vyara.fantasy.data.models.Binding;

import com.vyara.fantasy.services.validation.ValidImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateEditModel {

    @NotNull(message = "Field can not be empty")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String title;

    @Nullable
    @ValidImage
    private MultipartFile image;

    @NotNull(message = "Field can not be empty")
    @Size(min=30, max=600, message = "Please enter enter between 30 and 600 characters")
    private String description;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Author name should have only letters and space")
    @Size(min=5, max=100, message = "Please enter enter between 5 and 100 characters")
    private String author;

    @NotNull(message = "Field can not be empty")
    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Please enter date in valid period")
    private String releaseDate;

}
