package com.rehan.librarymanagementsystem.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name="books")
@Data
public class Book {

    @Id
    @Column(name="book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotNull
    @Size(min = 2 , max = 100)
    @Column(name="title")
    private String title;

    @Size(min=13 , max = 14)
    @Column(name="isbn")
    @NotNull
    private String ISBN;

    @NotNull
    @Past(message = "publicationDate can't be in future")
    @Column(name="publication_date")
    private java.time.LocalDate publicationDate;

    @NotNull
    @Size(min = 3 , max = 20)
    @Column(name="genre")
    private String genre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;
}
