package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService=authorService;
    }

    @GetMapping("/authors")
    public Iterable<Author> findAll() {
        return authorService.findAll();
    }
}
