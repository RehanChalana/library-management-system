package com.rehan.librarymanagementsystem.bookcopy.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CopyResponseDTO(int copyId , boolean isBorrowed ,java.time.LocalDate dueDate, Integer  userId , int bookId) {
}
