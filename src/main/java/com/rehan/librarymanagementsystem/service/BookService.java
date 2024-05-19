package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    public Book add(Book book) {
        return bookRepository.save(book);
    }

    public Book findById(int id) {
        return bookRepository.findById(id).get();
    }

    public void delete(int id){
        bookRepository.deleteById(id);
    }

    public void update(Book book){
        bookRepository.save(book);
    }

    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}