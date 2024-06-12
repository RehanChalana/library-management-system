package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.BookNotFoundException;
import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.service.AuthorService;
import com.rehan.librarymanagementsystem.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BookRestController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookRestController(BookService bookService,AuthorService authorService) {
        this.bookService=bookService;
        this.authorService=authorService;
    }

    @PostMapping("/books")
    public Book save(@RequestBody Book book) {
        return bookService.addNewBook(book);
    }

    @GetMapping("/books")
    public Iterable<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Book getBook(@PathVariable int bookId) {
        Optional<Book> book = bookService.findById(bookId);
        if(book.isEmpty()) throw new BookNotFoundException("Book with id: "+bookId+ " does not exists");
        return book.get();
    }

//    @PutMapping("/books")
//    public Book updateBook(@RequestBody Book book) {
//        int bookId = book.getBookId();
//        if(bookService.findById(bookId).isEmpty()) throw new BookNotFoundException("Book with bookId : "+bookId +" does not exists");
//        Book updatedBook = bookService.save(book);
//        return updatedBook;
//    }
//
//    @DeleteMapping("/books/{bookId}")
//    public void deleteBook(@PathVariable int bookId) {
//        Optional<Book> book = bookService.findById(bookId);
//        if(book.isEmpty()) throw new BookNotFoundException("Book with id: "+bookId+ " does not exists");
//        bookService.deleteById(bookId);
//    }

}
