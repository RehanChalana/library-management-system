package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.service.AuthorService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/authors/{authorId}")
    public Author findById(@PathVariable int authorId) {
        return authorService.findById(authorId);
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteById(@PathVariable int authorId) {
         authorService.deleteById(authorId);
    }

    @PostMapping("/authors")
    public Author saveNewAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody Author author) {
        return authorService.save(author);
    }

}
