package com.rehan.librarymanagementsystem.author.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AuthorRequestDTO(@NotNull @Size(min = 2 , max = 50) String name) {
}
