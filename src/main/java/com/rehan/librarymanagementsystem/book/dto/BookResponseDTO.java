package com.rehan.librarymanagementsystem.book.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookResponseDTO {

    @NotNull
    @Min(value = 1)
    private int bookId;

    @NotNull
    @Size(min = 2 , max = 100)
    private String title;

    @NotNull
    @Size(min = 13 , max = 14)
    private String ISBN;

    @NotNull
    @Past(message = "publicationDate can't be in future")
    private java.time.LocalDate publicationDate;

    @NotNull
    @Size(min = 3 , max = 20)
    private String genre;

    @Min(value = 1)
    @NotNull
    private int authorId;

}
