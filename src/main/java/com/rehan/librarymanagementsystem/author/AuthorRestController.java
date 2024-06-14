package com.rehan.librarymanagementsystem.author;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Author> saveNewAuthor(@RequestBody @Valid Author author) {
        Author createdAuthor =  authorService.addNewAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/authors")
    public Author updateAuthor(@RequestBody @Valid Author author) {
        return authorService.updateAuthor(author);
    }

}
