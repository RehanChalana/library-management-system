package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.service.BookService;
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
        return bookService.add(book);
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{book_id}")
    public Book getBook(@PathVariable int book_id) {
        return bookService.findById(book_id);
    }


}
