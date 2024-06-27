package com.rehan.librarymanagementsystem.book;

import com.rehan.librarymanagementsystem.author.AuthorService;
import com.rehan.librarymanagementsystem.book.dto.BookMapper;
import com.rehan.librarymanagementsystem.book.dto.BookRequestDTO;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository,AuthorService authorService,BookMapper bookMapper) {
        this.bookRepository=bookRepository;
        this.authorService=authorService;
        this.bookMapper=bookMapper;
    }


    public BookResponseDTO addNewBook(BookRequestDTO request) {
        int authorId = request.authorId();
//      for error handling if author does not exist
        authorService.findById(authorId);
        Book bookEntity = bookMapper.requestDTOtoBook(request);
        log.info("book entity : "+bookEntity.toString());
        Book savedEntity =  bookRepository.save(bookEntity);
        return bookMapper.bookToResponseDTO(savedEntity);
    }

    public BookResponseDTO updateBook(BookRequestDTO request,int bookId) {
        int authorId = request.authorId();
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+bookId+" does not exists"));
//      for error handling if author does not exist
        authorService.findById(authorId);
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
