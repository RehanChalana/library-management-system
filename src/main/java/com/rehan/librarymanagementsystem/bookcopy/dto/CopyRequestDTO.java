package com.rehan.librarymanagementsystem.bookcopy.dto;

import com.rehan.librarymanagementsystem.book.Book;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CopyRequestDTO(@NotNull boolean isBorrowed , @FutureOrPresent(message = "due date can not be in past") java.time.LocalDate dueDate,
                             @Positive Integer userId , @Positive @NotNull int bookId) {
}
