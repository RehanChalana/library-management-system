package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.BookCopy;
import com.rehan.librarymanagementsystem.repositories.BookCopyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookService bookService;

    public BookCopyService(BookCopyRepository bookCopyRepository,BookService bookService) {
        this.bookCopyRepository=bookCopyRepository;
        this.bookService=bookService;
    }

    public Iterable<BookCopy> findAll(){
        return bookCopyRepository.findAll();
    }

    public BookCopy findById(int id){
        return bookCopyRepository.findById(id).get();
    }

    public BookCopy save(BookCopy bookCopy) {
        bookCopy.setBook(bookService.findById(bookCopy.getBook().getBookId()));
        return bookCopyRepository.save(bookCopy);
    }

}
