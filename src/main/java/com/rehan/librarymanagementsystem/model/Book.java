package com.rehan.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="book")
@Data
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name="title")
    private String title;

    @Column(name="num_pages")
    private int numPages;

    @Column(name="publication_date")
    private java.time.LocalDate publicationDate;

    @Column(name="author_id")
    private int authorId;

    @Column(name="is_borrowed")
    private boolean isBorrowed;
}
