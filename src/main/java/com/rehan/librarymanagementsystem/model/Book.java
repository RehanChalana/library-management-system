package com.rehan.librarymanagementsystem.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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

    @Column(name="isbn")
    private String ISBN;

    @Column(name="publication_date")
    private java.time.LocalDate publicationDate;

    @Column(name="genre")
    private String genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
}
