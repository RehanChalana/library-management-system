package com.rehan.librarymanagementsystem.model;


import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

@Entity
@Table(name="book")
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
    
}
