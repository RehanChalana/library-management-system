package com.rehan.librarymanagementsystem.controller;

import com.rehan.librarymanagementsystem.model.BookCopy;
import com.rehan.librarymanagementsystem.service.BookCopyService;
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
    public BookCopy addNewCopy(@RequestBody BookCopy bookCopy){
        return bookCopyService.save(bookCopy);
    }

    @PutMapping("/bookcopies")
    public BookCopy update(@RequestBody BookCopy bookCopy) {
        return bookCopyService.save(bookCopy);
    }


}
