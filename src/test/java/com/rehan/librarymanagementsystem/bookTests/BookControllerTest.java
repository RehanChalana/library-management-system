package com.rehan.librarymanagementsystem.bookTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rehan.librarymanagementsystem.book.BookRestController;
import com.rehan.librarymanagementsystem.book.BookService;
import com.rehan.librarymanagementsystem.book.dto.BookRequestDTO;
import com.rehan.librarymanagementsystem.book.dto.BookResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookRestController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void getBookByIdTest() throws Exception {
        when(bookService.findById(1)).thenReturn(new BookResponseDTO(1,"title","1234567890123", LocalDate.of(2000,12,12),"genre",1));

        mockMvc.perform(get("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.ISBN").value("1234567890123"))
                .andExpect(jsonPath("$.publicationDate").value("2000-12-12"))
                .andExpect(jsonPath("$.genre").value("genre"))
                .andExpect(jsonPath("$.authorId").value(1));
        verify(bookService,times(1)).findById(1);

    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk());
        verify(bookService,times(1)).deleteById(1);
    }

    @Test
    public void addNewBookTest() throws Exception {
        BookRequestDTO requestDTO = new BookRequestDTO("title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        BookResponseDTO responseDTO = new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        when(bookService.addNewBook(requestDTO)).thenReturn(responseDTO);

       mockMvc.perform(post("/api/books")
               .contentType(MediaType.APPLICATION_JSON)
               .content(mapper.writeValueAsString(requestDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.bookId").value(1))
               .andExpect(jsonPath("$.title").value("title"))
               .andExpect(jsonPath("$.ISBN").value("1234567890123"))
               .andExpect(jsonPath("$.publicationDate").value("2000-12-12"))
               .andExpect(jsonPath("$.genre").value("genre"))
               .andExpect(jsonPath("$.authorId").value(1));

       verify(bookService,times(1)).addNewBook(requestDTO);
    }

    @Test
    public void updateBookTest() throws Exception {
        BookRequestDTO requestDTO = new BookRequestDTO("title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        BookResponseDTO responseDTO = new BookResponseDTO(1,"title","1234567890123",LocalDate.of(2000,12,12),"genre",1);
        when(bookService.updateBook(requestDTO,1)).thenReturn(responseDTO);
        mockMvc.perform(put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookId").value(1))
                .andExpect(jsonPath("$.title").value("title"))
                .andExpect(jsonPath("$.ISBN").value("1234567890123"))
                .andExpect(jsonPath("$.publicationDate").value("2000-12-12"))
                .andExpect(jsonPath("$.genre").value("genre"))
                .andExpect(jsonPath("$.authorId").value(1));
        verify(bookService,times(1)).updateBook(requestDTO,1);
    }

    @Test
    public void findAllTest() throws Exception {
        BookResponseDTO responseDTO1 = new BookResponseDTO(1,"title1","1234567890123",LocalDate.of(2000,12,12),"genre1",1);
        BookResponseDTO responseDTO2 = new BookResponseDTO(2,"title2","1234567890124",LocalDate.of(2000,11,11),"genre2",2);
        when(bookService.findAll()).thenReturn(Arrays.asList(responseDTO1,responseDTO2));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].bookId").value(1))
                .andExpect(jsonPath("$[0].title").value("title1"))
                .andExpect(jsonPath("$[0].ISBN").value("1234567890123"))
                .andExpect(jsonPath("$[0].publicationDate").value("2000-12-12"))
                .andExpect(jsonPath("$[0].genre").value("genre1"))
                .andExpect(jsonPath("$[0].authorId").value(1))
                .andExpect(jsonPath("$[1].bookId").value(2))
                .andExpect(jsonPath("$[1].title").value("title2"))
                .andExpect(jsonPath("$[1].ISBN").value("1234567890124"))
                .andExpect(jsonPath("$[1].publicationDate").value("2000-11-11"))
                .andExpect(jsonPath("$[1].genre").value("genre2"))
                .andExpect(jsonPath("$[1].authorId").value(2));

        verify(bookService,times(1)).findAll();
    }


}
