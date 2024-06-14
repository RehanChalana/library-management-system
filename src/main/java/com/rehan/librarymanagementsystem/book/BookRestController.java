package com.rehan.librarymanagementsystem.book;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    @Autowired
    public BookRestController(BookService bookService) {
        this.bookService=bookService;
    }

    @PostMapping("/books")
    public ResponseEntity<Book> save(@RequestBody @Valid Book book) {
        Book createdBook = bookService.addNewBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable int bookId) {
        return bookService.findById(bookId);
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody @Valid Book book) {
        return bookService.updateBook(book);
    }
    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteById(bookId);
    }

}
