package com.rehan.librarymanagementsystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Table(name="book_copies")
@Entity
public class BookCopy {

    @Id
    @Column(name="copy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copyId;

    @NotNull
    @Column(name = "is_borrowed")
    private boolean isBorrowed;

    @FutureOrPresent(message = "due date can not be in past")
    @Column(name = "due_date")
    private java.time.LocalDate dueDate;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

}
