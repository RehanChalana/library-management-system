package com.rehan.librarymanagementsystem.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="books")
@Data
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column(name="title")
    private String title;

    @Column(name="ISBN")
    private String ISBN;

    @Column(name="publication_date")
    private java.time.LocalDate publicationDate;

    @Column(name="author_id")
    private int authorId;

    @Column(name="genre")
    private String genre;
}
