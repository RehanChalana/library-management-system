package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.repositories.AuthorRepository;
import com.rehan.librarymanagementsystem.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public BookService(BookRepository bookRepository,AuthorRepository authorRepository) {
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
    }

    public Book addNewBook(Book book) {
        book.setBookId(0);
        int authorId = book.getAuthor().getAuthorId();
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists");
        return bookRepository.save(book);
    }

    public Optional<Book> findById(int id) {
        return bookRepository.findById(id);
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }


    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}
