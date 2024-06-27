package com.rehan.librarymanagementsystem.authorTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rehan.librarymanagementsystem.author.AuthorRestController;
import com.rehan.librarymanagementsystem.author.AuthorService;
import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorRestController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private AuthorService authorService;

    @Test
    public void getAuthorByIdTest() throws Exception {
        when(authorService.findById(1)).thenReturn(new AuthorResponseDTO(1,"name"));
        mockMvc.perform(get("/api/authors/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.name").value("name"));

        verify(authorService,times(1)).findById(1);
    }

    @Test
    public void findAllTest() throws Exception {
        AuthorResponseDTO responseDTO1 = new AuthorResponseDTO(1,"name1");
        AuthorResponseDTO responseDTO2 = new AuthorResponseDTO(2,"name2");
        when(authorService.findAll()).thenReturn(Arrays.asList(responseDTO1,responseDTO2));

        mockMvc.perform(get("/api/authors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].authorId").value(1))
                .andExpect(jsonPath("$[0].name").value("name1"))
                .andExpect(jsonPath("$[1].authorId").value(2))
                .andExpect(jsonPath("$[1].name").value("name2"));

        verify(authorService,times(1)).findAll();
    }

    @Test
    public void saveNewAuthorTest() throws Exception {
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("name");
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(1,"name");

        when(authorService.addNewAuthor(authorRequestDTO)).thenReturn(authorResponseDTO);

        mockMvc.perform(post("/api/authors")
                .content(mapper.writeValueAsString(authorRequestDTO))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.name").value("name"));

        verify(authorService,times(1)).addNewAuthor(authorRequestDTO);
    }

    @Test
    public void deleteByIdTest() throws Exception {
        mockMvc.perform(delete("/api/authors/1"))
                .andExpect(status().isOk());
        verify(authorService,times(1)).deleteById(1);
    }

    @Test
    public void updateAuthorTest() throws Exception {
        AuthorRequestDTO authorRequestDTO = new AuthorRequestDTO("name");
        AuthorResponseDTO authorResponseDTO = new AuthorResponseDTO(1,"name");
        when(authorService.updateAuthor(authorRequestDTO,1)).thenReturn(authorResponseDTO);

        mockMvc.perform(put("/api/authors/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(authorRequestDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.authorId").value(1))
                .andExpect(jsonPath("$.name").value("name"));

        verify(authorService,times(1)).updateAuthor(authorRequestDTO,1);
    }


}
