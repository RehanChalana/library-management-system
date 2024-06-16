package com.rehan.librarymanagementsystem.author;

import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuthorRestController {

    private final AuthorService authorService;

    public AuthorRestController(AuthorService authorService) {
        this.authorService=authorService;
    }

    @GetMapping("/authors")
    public List<AuthorResponseDTO> findAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{authorId}")
    public AuthorResponseDTO findById(@PathVariable int authorId) {
        return authorService.findById(authorId);
    }

    @DeleteMapping("/authors/{authorId}")
    public void deleteById(@PathVariable int authorId) {
        authorService.deleteById(authorId);
    }

    @PostMapping("/authors")
    public ResponseEntity<AuthorResponseDTO> saveNewAuthor(@RequestBody @Valid AuthorRequestDTO author) {
        AuthorResponseDTO createdAuthor =  authorService.addNewAuthor(author);
        return new ResponseEntity<>(createdAuthor, HttpStatus.CREATED);
    }

    @PutMapping("/authors/{authorId}")
    public AuthorResponseDTO updateAuthor(@RequestBody @Valid AuthorRequestDTO author,@PathVariable int authorId) {
        return authorService.updateAuthor(author,authorId);
    }

}
