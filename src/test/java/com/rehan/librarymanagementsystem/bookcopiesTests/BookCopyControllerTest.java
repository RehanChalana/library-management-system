package com.rehan.librarymanagementsystem.bookcopiesTests;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyController;
import com.rehan.librarymanagementsystem.bookcopy.BookCopyService;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyRequestDTO;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyResponseDTO;
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

@WebMvcTest(BookCopyController.class)
public class BookCopyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookCopyService bookCopyService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    public void findByIdBorrowedTest() throws Exception {
        when(bookCopyService.findById(1)).thenReturn(new CopyResponseDTO(1,true, LocalDate.of(2025,12,12),4,3));

        mockMvc.perform(get("/api/bookcopies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(1))
                .andExpect(jsonPath("$.isBorrowed").value(true))
                .andExpect(jsonPath("$.dueDate").value("2025-12-12"))
                .andExpect(jsonPath("$.userId").value(4))
                .andExpect(jsonPath("$.bookId").value(3));
        verify(bookCopyService,times(1)).findById(1);
    }

    @Test
    public void findByIdNotBorrowedTest() throws  Exception {
        when(bookCopyService.findById(1)).thenReturn(new CopyResponseDTO(1,false,null,null,4));

        mockMvc.perform(get("/api/bookcopies/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(1))
                .andExpect(jsonPath("$.isBorrowed").value(false))
                .andExpect(jsonPath("$.dueDate").doesNotExist())
                .andExpect(jsonPath("$.userId").doesNotExist())
                .andExpect(jsonPath("$.bookId").value(4));
        verify(bookCopyService,times(1)).findById(1);
    }

    @Test
    public void addNewCopyTest() throws Exception {
        CopyRequestDTO requestDTO = new CopyRequestDTO(false,null,null,5);
        CopyResponseDTO responseDTO =  new CopyResponseDTO(2,false,null,null,5);
        when(bookCopyService.addNewCopy(requestDTO)).thenReturn(responseDTO);

        mockMvc.perform(post("/api/bookcopies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.copyId").value(2))
                .andExpect(jsonPath("$.isBorrowed").value(false))
                .andExpect(jsonPath("$.userId").doesNotExist())
                .andExpect(jsonPath("$.dueDate").doesNotExist())
                .andExpect(jsonPath("$.bookId").value(5));
        verify(bookCopyService,times(1)).addNewCopy(requestDTO);
    }

    @Test
    public void deleteCopyTest() throws Exception {
        mockMvc.perform(delete("/api/bookcopies/1"))
                .andExpect(status().isOk());
        verify(bookCopyService,times(1)).deleteById(1);
    }

    @Test
    public void updateCopyTest() throws Exception {
        CopyRequestDTO requestDTO = new CopyRequestDTO(true,LocalDate.of(2025,12,12),5,10);
        CopyResponseDTO responseDTO = new CopyResponseDTO(12,true,LocalDate.of(2025,12,12),5,10);
        when(bookCopyService.updateCopy(requestDTO,12)).thenReturn(responseDTO);
        mockMvc.perform(put("/api/bookcopies/12")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.copyId").value(12))
                .andExpect(jsonPath("$.isBorrowed").value(true))
                .andExpect(jsonPath("$.dueDate").value("2025-12-12"))
                .andExpect(jsonPath("$.userId").value(5))
                .andExpect(jsonPath("$.bookId").value(10));
        verify(bookCopyService,times(1)).updateCopy(requestDTO,12);
    }

    @Test
    public void findAllTest() throws Exception {
        CopyResponseDTO responseDTO1 = new CopyResponseDTO(12,true,LocalDate.of(2025,12,12),5,10);
        CopyResponseDTO responseDTO2 =  new CopyResponseDTO(2,false,null,null,5);
        when(bookCopyService.findAll()).thenReturn(Arrays.asList(responseDTO1,responseDTO2));

        mockMvc.perform(get("/api/bookcopies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].copyId").value(12))
                .andExpect(jsonPath("$[0].isBorrowed").value(true))
                .andExpect(jsonPath("$[0].dueDate").value("2025-12-12"))
                .andExpect(jsonPath("$[0].userId").value(5))
                .andExpect(jsonPath("$[0].bookId").value(10))
                .andExpect(jsonPath("$[1].copyId").value(2))
                .andExpect(jsonPath("$[1].isBorrowed").value(false))
                .andExpect(jsonPath("$[1].dueDate").doesNotExist())
                .andExpect(jsonPath("$[1].userId").doesNotExist())
                .andExpect(jsonPath("$[1].bookId").value(5));

        verify(bookCopyService,times(1)).findAll();


    }
 }
