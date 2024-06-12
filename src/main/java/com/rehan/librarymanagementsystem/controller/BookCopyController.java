package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.BookCopy;
import com.rehan.librarymanagementsystem.service.BookCopyService;
import com.rehan.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookCopyController {
    private final BookCopyService bookCopyService;

    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService=bookCopyService;
    }

    @GetMapping("/bookcopies")
    public Iterable<BookCopy> findALl(){
        return bookCopyService.findAll();
    }

    @GetMapping("/bookcopies/{copyId}")
    public BookCopy findById(@PathVariable int copyId) {
        return bookCopyService.findById(copyId);
    }

    @PostMapping("/bookcopies")
    public ResponseEntity<BookCopy> addNewCopy(@RequestBody @Valid BookCopy bookCopy){
        BookCopy createdBook =  bookCopyService.addNewCopy(bookCopy);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/bookcopies")
    public BookCopy update(@RequestBody @Valid  BookCopy bookCopy) {
        return bookCopyService.updateCopy(bookCopy);
    }

    @DeleteMapping("/bookcopies/{copyId}")
    public void delete(@PathVariable int copyId) {
         bookCopyService.deleteCopy(copyId);
    }


}
