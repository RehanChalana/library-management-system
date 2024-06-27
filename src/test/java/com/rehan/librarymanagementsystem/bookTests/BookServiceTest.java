package com.rehan.librarymanagementsystem.bookTests;


import com.rehan.librarymanagementsystem.author.AuthorService;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import com.rehan.librarymanagementsystem.book.Book;
import com.rehan.librarymanagementsystem.book.BookRepository;
import com.rehan.librarymanagementsystem.book.BookService;
import com.rehan.librarymanagementsystem.book.dto.BookMapper;
import com.rehan.librarymanagementsystem.book.dto.BookRequestDTO;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorService authorService;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookService bookService;

    @Test
    public void findByIdSuccessTest() {
        Book book = new Book();
        BookResponseDTO responseDTO = new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        when(bookRepository.findById(1)).thenReturn(Optional.of(book));
        when(bookMapper.bookToResponseDTO(book)).thenReturn(responseDTO);

        BookResponseDTO result = bookService.findById(1);

        assertNotNull(result);
        assertEquals(1,result.bookId());
        assertEquals("title",result.title());
        assertEquals("1234567890123",result.ISBN());
        assertEquals(LocalDate.of(2000,12,12),result.publicationDate());
        assertEquals("genre",result.genre());
        verify(bookRepository,times(1)).findById(1);
    }

    @Test
    public void findByIdNotFoundTest() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class,() -> bookService.findById(1));
        verify(bookRepository,times(1)).findById(1);
        verify(bookRepository,never()).deleteById(1);
    }

    @Test
    public void deleteByIdSuccessTest() {
        when(bookRepository.findById(1)).thenReturn(Optional.of(new Book()));
        bookService.deleteById(1);
        verify(bookRepository,times(1)).findById(1);
        verify(bookRepository,times(1)).deleteById(1);
    }

    @Test
    public void deleteByIdNotFoundTest() {
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.deleteById(1));
        verify(bookRepository,times(1)).findById(1);
        verify(bookRepository,never()).deleteById(1);
    }

    @Test
    public void addNewBookSuccessTest() {
        BookRequestDTO requestDTO = new BookRequestDTO("title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        Book bookEntity = new Book();
        Book savedEntity = new Book();
        BookResponseDTO responseDTO = new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        when(authorService.findById(1)).thenReturn(new AuthorResponseDTO(1,"name"));
        when(bookMapper.requestDTOtoBook(requestDTO)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(savedEntity);
        when(bookMapper.bookToResponseDTO(savedEntity)).thenReturn(responseDTO);

        BookResponseDTO result = bookService.addNewBook(requestDTO);

        assertNotNull(result);
        assertEquals(1,result.bookId());
        assertEquals("title",result.title());
        assertEquals("1234567890123",result.ISBN());
        assertEquals(LocalDate.of(2000,12,12),result.publicationDate());
        assertEquals("genre",result.genre());
        assertEquals(1,result.authorId());

        verify(bookRepository,times(1)).save(savedEntity);
        verify(bookMapper,times(1)).bookToResponseDTO(savedEntity);
        verify(bookMapper,times(1)).requestDTOtoBook(requestDTO);
        verify(authorService,times(1)).findById(1);
    }

    @Test
    public void updateBookSuccessTest() {
        BookRequestDTO requestDTO = new BookRequestDTO("title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        BookResponseDTO responseDTO = new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        Book bookEntity = new Book();
        Book savedEntity = new Book();
        when(bookRepository.findById(1)).thenReturn(Optional.of(new Book()));
        when(authorService.findById(1)).thenReturn(new AuthorResponseDTO(1,"name"));
        when(bookMapper.requestDTOtoBook(requestDTO)).thenReturn(bookEntity);
        when(bookRepository.save(bookEntity)).thenReturn(savedEntity);
        when(bookMapper.bookToResponseDTO(savedEntity)).thenReturn(responseDTO);


       BookResponseDTO result =  bookService.updateBook(requestDTO,1);

       assertNotNull(result);
       assertEquals(1,result.bookId());
       assertEquals("title",result.title());
       assertEquals("1234567890123",result.ISBN());
       assertEquals(LocalDate.of(2000,12,12),result.publicationDate());
       assertEquals("genre",result.genre());
       assertEquals(1,result.authorId());

       verify(bookRepository,times(1)).save(bookEntity);
       verify(bookMapper,times(1)).requestDTOtoBook(requestDTO);
       verify(bookMapper,times(1)).bookToResponseDTO(savedEntity);
       verify(authorService,times(1)).findById(1);

    }

    @Test
    public void updateBookNotFoundTest() {
        BookRequestDTO requestDTO = new BookRequestDTO("title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        when(bookRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(requestDTO,1));
        verify(bookRepository,never()).save(any(Book.class));
        verify(bookMapper,never()).requestDTOtoBook(requestDTO);
        verify(bookMapper,never()).bookToResponseDTO(any(Book.class));
    }

    @Test
    public void findAllTest() {
        Book book1 = new Book();
        Book book2 = new Book();
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1,book2));
        when(bookMapper.bookToResponseDTO(any(Book.class))).thenReturn(new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1));

        List<BookResponseDTO> result = bookService.findAll();

        assertEquals(2,result.size());
        verify(bookRepository,times(1)).findAll();
    }


}
