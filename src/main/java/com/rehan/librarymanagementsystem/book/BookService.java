package com.rehan.librarymanagementsystem.book;

import com.rehan.librarymanagementsystem.author.AuthorRepository;
import com.rehan.librarymanagementsystem.book.dto.BookMapper;
import com.rehan.librarymanagementsystem.book.dto.BookRequestDTO;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository,AuthorRepository authorRepository,BookMapper bookMapper) {
        this.bookRepository=bookRepository;
        this.authorRepository=authorRepository;
        this.bookMapper=bookMapper;
    }


    public BookResponseDTO addNewBook(BookRequestDTO request) {
        int authorId = request.authorId();
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists");
        Book bookEntity = bookMapper.requestDTOtoBook(request);
        log.info("book entity : "+bookEntity.toString());
//        setting bookId to 0 because we are creating a new entry
        bookEntity.setBookId(0);
        Book savedEntity =  bookRepository.save(bookEntity);
        BookResponseDTO response = bookMapper.bookToResponseDTO(savedEntity);
        return response;
    }

    public BookResponseDTO updateBook(BookRequestDTO request,int bookId) {
        int authorId = request.authorId();
        if(bookRepository.findById(bookId).isEmpty()) throw new BookNotFoundException("Book with bookId : "+bookId+" does not exists");
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+"does not exists");
        Book bookEntity = bookMapper.requestDTOtoBook(request);
        bookEntity.setBookId(bookId);
        Book savedEntity = bookRepository.save(bookEntity);
        BookResponseDTO response = bookMapper.bookToResponseDTO(savedEntity);
        return response;
    }

    public BookResponseDTO findById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) throw new BookNotFoundException("Book with bookId : "+id+" does not exists");
        return bookMapper.bookToResponseDTO(book.get());
    }

    public void deleteById(int id){
        Optional<Book> book = bookRepository.findById(id);
        if(book.isEmpty()) throw new BookNotFoundException("Book with bookId : "+id+" does not exists");
        bookRepository.deleteById(id);
    }


    public List<BookResponseDTO> findAll() {
        List<Book> books =  bookRepository.findAll();
        return books.stream().map(bookMapper::bookToResponseDTO).collect(Collectors.toList());
    }

}
