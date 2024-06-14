package com.rehan.librarymanagementsystem.book;

import com.rehan.librarymanagementsystem.book.dto.BookRequestDTO;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService=bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<BookResponseDTO> save(@RequestBody @Valid BookRequestDTO book) {
        BookResponseDTO createdBook = bookService.addNewBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public List<BookResponseDTO> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public BookResponseDTO getBook(@PathVariable int bookId) {
        return bookService.findById(bookId);
    }

    @PutMapping("/books/{bookId}")
    public BookResponseDTO updateBook(@RequestBody @Valid BookRequestDTO book,@PathVariable int bookId) {
        return bookService.updateBook(book,bookId);
    }
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteById(bookId);
    }

}
