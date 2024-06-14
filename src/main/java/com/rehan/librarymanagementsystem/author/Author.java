package com.rehan.librarymanagementsystem.author;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
@Table(name="authors")
public class Author {

    @Id
    @Column(name="author_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;

    @NotNull
    @Size(min = 2 , max = 50,message = "invalid name length for author")
    @Column(name="name")
    private String name;

}
