package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.service.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class BookRestController {

    private BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService=bookService;
    }

    @GetMapping("/books")
    public Iterable<Book> books() {
        return bookService.findAll();
    }
}
