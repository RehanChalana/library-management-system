package com.rehan.librarymanagementsystem.book;

import com.rehan.librarymanagementsystem.author.AuthorRepository;
import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public Book addNewBook(Book book) {
        book.setBookId(0);
        int authorId = book.getAuthor().getAuthorId();
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists");
        return bookRepository.save(book);
    }

    public Book updateBook(Book book) {
        int bookId = book.getBookId();
        int authorId = book.getAuthor().getAuthorId();
        if(bookRepository.findById(bookId).isEmpty()) throw new BookNotFoundException("Book with bookId : "+bookId+" does not exists");
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+"does not exists");
        return bookRepository.save(book);
    }

    public Book findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) throw new BookNotFoundException("Book with bookId : "+id+" does not exists");
        return book.get();
    }

    public void deleteById(int id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) throw new BookNotFoundException("Book with bookId : "+id+" does not exists");
        bookRepository.deleteById(id);
    }


    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }

}
