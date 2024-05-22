package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.model.Book;
import com.rehan.librarymanagementsystem.repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    public BookService(BookRepository bookRepository,AuthorService authorService) {
        this.bookRepository=bookRepository;
        this.authorService=authorService;
    }

    public Book save(Book book) {
        Author author = authorService.findById(book.getAuthor().getAuthorId());
        book.setAuthor(author);
        Book saved = bookRepository.save(book);
        log.info("Saved book with author id: " + saved.getAuthor());
        return saved;
    }

    public Book findById(int id) {
        return bookRepository.findById(id).get();
    }

    public void deleteById(int id){
        bookRepository.deleteById(id);
    }


    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}
