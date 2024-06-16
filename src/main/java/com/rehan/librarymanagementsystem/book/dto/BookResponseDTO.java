package com.rehan.librarymanagementsystem.book.dto;

import jakarta.validation.constraints.*;
import lombok.Data;


public record BookResponseDTO(@NotNull @Positive int bookId, @NotNull @Size(min = 2, max = 100) String title,
                              @NotNull @Size(min = 13, max = 14) String ISBN, @Past @NotNull java.time.LocalDate publicationDate,
                              @NotNull @Size(min = 3, max = 20) String genre, @Positive @NotNull int authorId) {

}
