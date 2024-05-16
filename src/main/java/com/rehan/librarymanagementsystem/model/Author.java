package com.rehan.librarymanagementsystem.model;


import jakarta.persistence.*;

@Entity
@Table(name="authors")
public class Author {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int author_id;

    @Column(name="name")
    private String title;
}
