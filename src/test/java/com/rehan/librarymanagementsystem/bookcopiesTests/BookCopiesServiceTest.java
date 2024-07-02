package com.rehan.librarymanagementsystem.bookcopiesTests;

import com.rehan.librarymanagementsystem.book.Book;
import com.rehan.librarymanagementsystem.book.BookService;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import com.rehan.librarymanagementsystem.bookcopy.BookCopy;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyRepository;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyService;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyMapper;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyRequestDTO;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.BookCopyNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

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

    @Test
    public void findByIdNotFoundTest() {
        when(bookCopyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookCopyNotFoundException.class, () -> bookCopyService.findById(1));
        verify(bookCopyRepository,times(1)).findById(1);
    }

    @Test
    public void deleteByIdSuccessTest() {
        when(bookCopyRepository.findById(1)).thenReturn(Optional.of(new BookCopy()));
        bookCopyService.deleteById(1);
        verify(bookCopyRepository,times(1)).deleteById(1);
        verify(bookCopyRepository,times(1)).findById(1);
    }

    @Test
    public void deleteByIdNotFoundTest() {
        when(bookCopyRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(BookCopyNotFoundException.class,() -> bookCopyService.deleteById(1));
        verify(bookCopyRepository,times(1)).findById(1);
        verify(bookCopyRepository,never()).deleteById(1);
    }

    @Test
    public void findAllTest() {
        BookCopy copy1 = new BookCopy();
        BookCopy copy2 = new BookCopy();
        CopyResponseDTO responseDTO = new CopyResponseDTO(1,true,LocalDate.of(2000,12,12),2,3);
        when(bookCopyRepository.findAll()).thenReturn(Arrays.asList(copy1,copy2));
        when(copyMapper.CopyToResponseDTO(any(BookCopy.class))).thenReturn(responseDTO);

        List<CopyResponseDTO> result = bookCopyService.findAll();

        assertEquals(2,result.size());
        verify(bookCopyRepository,times(1)).findAll();
    }

    @Test
    public void addNewCopyBorrowedTest() {
        CopyRequestDTO requestDTO = new CopyRequestDTO(true,LocalDate.of(2000,12,12),5,1);
        CopyResponseDTO responseDTO = new CopyResponseDTO(1,true,LocalDate.of(2000,12,12),5,1);
        BookCopy copyEntity = new BookCopy();
        BookCopy savedEntity = new BookCopy();
        when(bookService.findById(1)).thenReturn(new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1));
        when(copyMapper.RequestDTOtoCopy(requestDTO)).thenReturn(copyEntity);
        when(bookCopyRepository.save(copyEntity)).thenReturn(savedEntity);
        when(copyMapper.CopyToResponseDTO(savedEntity)).thenReturn(responseDTO);

        CopyResponseDTO result = bookCopyService.addNewCopy(requestDTO);

        assertNotNull(result);
        assertEquals(1,result.copyId());
        assertTrue(result.isBorrowed());
        assertEquals(LocalDate.of(2000,12,12),result.dueDate());
        assertEquals(5,result.userId());
        assertEquals(1,result.bookId());

        verify(bookCopyRepository,times(1)).save(copyEntity);
        verify(copyMapper,times(1)).RequestDTOtoCopy(requestDTO);
        verify(copyMapper,times(1)).CopyToResponseDTO(copyEntity);
        verify(bookService,times(1)).findById(1);
    }

    @Test
    public void addNewCopyNotBorrowedTest() {
        CopyRequestDTO requestDTO = new CopyRequestDTO(false,null,null,2);
        CopyResponseDTO responseDTO = new CopyResponseDTO(1,false,null,null,2);
        BookCopy copyEntity = new BookCopy();
        BookCopy savedEntity = new BookCopy();

        when(bookService.findById(2)).thenReturn(new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1));
        when(copyMapper.RequestDTOtoCopy(requestDTO)).thenReturn(copyEntity);
        when(bookCopyRepository.save(copyEntity)).thenReturn(savedEntity);
        when(copyMapper.CopyToResponseDTO(savedEntity)).thenReturn(responseDTO);

        CopyResponseDTO result = bookCopyService.addNewCopy(requestDTO);

        assertNotNull(result);
        assertFalse(result.isBorrowed());
        assertNull(result.userId());
        assertNull(result.dueDate());
        assertEquals(2,result.bookId());
        assertEquals(1,result.copyId());

        verify(bookService,times(1)).findById(2);
        verify(bookCopyRepository,times(1)).save(copyEntity);
        verify(copyMapper,times(1)).RequestDTOtoCopy(requestDTO);
        verify(copyMapper,times(1)).CopyToResponseDTO(savedEntity);
    }


    @Test
    public void updateCopyTest() {
        CopyRequestDTO requestDTO = new CopyRequestDTO(true,LocalDate.of(2000,12,12),4,2);
        CopyResponseDTO responseDTO = new CopyResponseDTO(2,true,LocalDate.of(2000,12,12),4,2);
        BookCopy copyEntity = new BookCopy();
        BookCopy savedEntity = new BookCopy();


        when(bookService.findById(2)).thenReturn(new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1));
        when(bookCopyRepository.findById(2)).thenReturn(Optional.of(new BookCopy()));
        when(copyMapper.RequestDTOtoCopy(requestDTO)).thenReturn(copyEntity);
        when(bookCopyRepository.save(copyEntity)).thenReturn(savedEntity);
        when(copyMapper.CopyToResponseDTO(savedEntity)).thenReturn(responseDTO);

        CopyResponseDTO result = bookCopyService.updateCopy(requestDTO,2);

        assertNotNull(result);
        assertEquals(2,result.copyId());
        assertEquals(LocalDate.of(2000,12,12),result.dueDate());
        assertTrue(result.isBorrowed());
        assertEquals(4,result.userId());
        assertEquals(2,result.bookId());
    }

}
