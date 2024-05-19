package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService=bookService;
    }

    @PostMapping("/books")
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable int bookId) {

        Book book = bookService.findById(bookId);
        if(book==null) throw new EntityNotFoundException("id - "+bookId);
        return book;
    }

    @PutMapping("/books")
    public Book updateBook(@RequestBody Book book) {
        Book updatedBook = bookService.save(book);
        return updatedBook;
    }

    @DeleteMapping("/books/{bookId}")
    public void deleteBook(@PathVariable int bookId) {
        bookService.deleteById(bookId);
    }

}
