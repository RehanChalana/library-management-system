package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository=bookRepository;
    }

    public void add(Book book) {
        bookRepository.save(book);
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

//    public Book getBook(int id) {
//        return bookRepository.findById(id);
//    }
}
