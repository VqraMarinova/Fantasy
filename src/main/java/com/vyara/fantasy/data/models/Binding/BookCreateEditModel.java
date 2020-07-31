package com.vyara.fantasy.data.models.Binding;

import com.vyara.fantasy.services.validation.ValidImage;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class BookCreateEditModel {

    @NotNull
    @Size(min=3, max=30)
    private String title;

    @NotNull
    @ValidImage
    private MultipartFile image;

    @NotNull
    @Size(min=10, max=600)
    private String description;

    @Pattern(regexp = "^[A-Za-z\\s]+$", message = "Author name should have only letters and space")
    private String author;

    @Pattern(regexp = "^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Please enter date in valid period")
    private String releaseDate;

}
