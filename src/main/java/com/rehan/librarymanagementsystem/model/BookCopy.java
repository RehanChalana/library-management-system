package com.rehan.librarymanagementsystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name="book_copies")
@Entity
public class BookCopy {

    @Id
    @Column(name="copy_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int copyId;

    @Column(name = "is_borrowed")
    private boolean isBorrowed;

    @Column(name = "due_date")
    private java.time.LocalDate dueDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;
}
