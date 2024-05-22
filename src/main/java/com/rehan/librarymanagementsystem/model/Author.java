package com.rehan.librarymanagementsystem.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name="authors")
public class Author {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @Column(name="name")
    private String name;


}
