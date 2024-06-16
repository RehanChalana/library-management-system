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
        authorRepository.findById(authorId).orElseThrow( () ->  new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists"));
        Book bookEntity = bookMapper.requestDTOtoBook(request);
        log.info("book entity : "+bookEntity.toString());
//        setting bookId to 0 because we are creating a new entry
        bookEntity.setBookId(0);
        Book savedEntity =  bookRepository.save(bookEntity);
        return bookMapper.bookToResponseDTO(savedEntity);
    }

    public BookResponseDTO updateBook(BookRequestDTO request,int bookId) {
        int authorId = request.authorId();
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+bookId+" does not exists"));
        authorRepository.findById(authorId).orElseThrow( () ->  new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists"));
        Book bookEntity = bookMapper.requestDTOtoBook(request);
        bookEntity.setBookId(bookId);
        Book savedEntity = bookRepository.save(bookEntity);
        return  bookMapper.bookToResponseDTO(savedEntity);

    }

    public BookResponseDTO findById(int id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+id+" does not exists"));
        return bookMapper.bookToResponseDTO(book);
    }

    public void deleteById(int id){
        bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+id+" does not exists"));
        bookRepository.deleteById(id);
    }


    public List<BookResponseDTO> findAll() {
        List<Book> books =  bookRepository.findAll();
        return books.stream().map(bookMapper::bookToResponseDTO).collect(Collectors.toList());
    }

}
