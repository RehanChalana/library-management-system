package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.exceptions.BookCopyNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.BookNotFoundException;
import com.rehan.librarymanagementsystem.model.BookCopy;
import com.rehan.librarymanagementsystem.repositories.BookCopyRepository;
import com.rehan.librarymanagementsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository,BookRepository bookRepository) {
        this.bookCopyRepository=bookCopyRepository;
        this.bookRepository=bookRepository;
    }

    public Iterable<BookCopy> findAll(){
        return bookCopyRepository.findAll();
    }

    public BookCopy findById(int id){
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(id);
        if(bookCopy.isEmpty()) throw new BookCopyNotFoundException("book copy with copyId : "+id+" does not exists");
        return bookCopy.get();
    }

    public BookCopy addNewCopy(BookCopy bookCopy) {
        bookCopy.setCopyId(0);
        int bookId = bookCopy.getBook().getBookId();
        if(bookRepository.findById(bookId).isEmpty()) throw new BookNotFoundException("Book with bookId : "+bookId+" does not exists");
        return bookCopyRepository.save(bookCopy);
    }

    public BookCopy updateCopy(BookCopy bookCopy) {
        int bookId = bookCopy.getBook().getBookId();
        int copyId = bookCopy.getCopyId();
        if(bookRepository.findById(bookId).isEmpty()) throw new BookNotFoundException("Book with bookId : "+bookId+" does not exists");
        if(bookCopyRepository.findById(copyId).isEmpty()) throw new BookCopyNotFoundException("book copy with copyId : "+copyId+" does not exists");
        return bookCopyRepository.save(bookCopy);
    }

    public void deleteCopy(int copyId) {
        Optional<BookCopy> bookCopy = bookCopyRepository.findById(copyId);
        if(bookCopy.isEmpty()) throw new BookCopyNotFoundException("book copy with copyId : "+copyId+" does not exists");
        bookCopyRepository.deleteById(copyId);
    }

}
