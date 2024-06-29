package com.rehan.librarymanagementsystem.bookcopiesTests;

import com.rehan.librarymanagementsystem.book.BookService;
import com.rehan.librarymanagementsystem.bookcopy.BookCopy;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyRepository;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyService;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyMapper;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookCopiesServiceTest {

    @Mock
    private BookCopyRepository bookCopyRepository;

    @Mock
    private BookService bookService;

    @Mock
    private CopyMapper copyMapper;

    @InjectMocks
    private BookCopyService bookCopyService;

    @Test
    public void findByIdSuccessTest() {
        BookCopy copy = new BookCopy();
        CopyResponseDTO responseDTO = new CopyResponseDTO(1,true, LocalDate.of(2025,12,12),2,3);
        when(bookCopyRepository.findById(1)).thenReturn(Optional.of(copy));
        when(copyMapper.CopyToResponseDTO(copy)).thenReturn(responseDTO);

        CopyResponseDTO result = bookCopyService.findById(1);

        assertNotNull(result);
        assertEquals(1,result.copyId());
        assertTrue(result.isBorrowed());
        assertEquals(LocalDate.of(2025,12,12),result.dueDate());
        assertEquals(3,result.bookId());
        assertEquals(2,result.userId());

        verify(bookCopyRepository,times(1)).findById(1);
        verify(copyMapper,times(1)).CopyToResponseDTO(copy);
    }
}
