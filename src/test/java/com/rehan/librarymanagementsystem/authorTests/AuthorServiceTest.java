package com.rehan.librarymanagementsystem.authorTests;

import com.rehan.librarymanagementsystem.author.Author;
import com.rehan.librarymanagementsystem.author.AuthorRepository;
import com.rehan.librarymanagementsystem.author.AuthorService;
import com.rehan.librarymanagementsystem.author.dto.AuthorMapper;
import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorService authorService;

    @Test
    public void testFindAll() {
        List<Author> authors = Arrays.asList(new Author() , new Author());
        when(authorRepository.findAll()).thenReturn(authors);
        when(authorMapper.authortoAuthorResponseDTO(any(Author.class))).thenReturn(new AuthorResponseDTO(1,"name"));
        List<AuthorResponseDTO> result = authorService.findAll();
        assertEquals(2,result.size());
        verify(authorRepository,times(1)).findAll();
    }

    @Test
    public void findByIdSuccess() {
        Author author = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(author));
        when(authorMapper.authortoAuthorResponseDTO(author)).thenReturn(new AuthorResponseDTO(1,"name"));
        AuthorResponseDTO result = authorService.findById(1);
        assertNotNull(result);
        assertEquals(1,result.authorId());
        assertEquals("name",result.name());
        verify(authorRepository,times(1)).findById(1);
    }

    @Test
    public void findByIdNotFound() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> authorService.findById(1));
        verify(authorRepository,times(1)).findById(1);
    }

    @Test
    public void deleteByIdSuccess() {
        when(authorRepository.findById(1)).thenReturn(Optional.of(new Author()));
        authorService.deleteById(1);
        verify(authorRepository,times(1)).findById(1);
        verify(authorRepository,times(1)).deleteById(1);
    }

    @Test
    public void deleteByIdFailure() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class,() -> authorService.deleteById(1));
        verify(authorRepository,times(1)).findById(1);
        verify(authorRepository,never()).deleteById(anyInt());
    }

    @Test
    public void addNewAuthorTest() {
       AuthorRequestDTO requestDTO = new AuthorRequestDTO("name");
       Author author = new Author();
       Author savedAuthor = new Author();

       when(authorMapper.authorRequestDTOToAuthor(requestDTO)).thenReturn(author);
       when(authorRepository.save(author)).thenReturn(savedAuthor);
       when(authorMapper.authortoAuthorResponseDTO(savedAuthor)).thenReturn(new AuthorResponseDTO(1,"name"));

       AuthorResponseDTO result = authorService.addNewAuthor(requestDTO);
       assertNotNull(result);
       verify(authorRepository,times(1)).save(author);
    }

    @Test
    public void updateAuthorSuccess() {
        AuthorRequestDTO requestDTO = new AuthorRequestDTO("name");
        Author authorEntity = new Author();
        when(authorRepository.findById(1)).thenReturn(Optional.of(new Author()));
        when(authorMapper.authorRequestDTOToAuthor(requestDTO)).thenReturn(authorEntity);
        authorEntity.setAuthorId(1);
        when(authorRepository.save(authorEntity)).thenReturn(authorEntity);
        when(authorMapper.authortoAuthorResponseDTO(authorEntity)).thenReturn(new AuthorResponseDTO(1,"name"));

        AuthorResponseDTO result = authorService.updateAuthor(requestDTO,1);
        assertNotNull(result);
        verify(authorRepository,times(1)).findById(1);
        verify(authorRepository,times(1)).save(authorEntity);
    }

    @Test
    public void updateAuthorNotFound() {
        when(authorRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(AuthorNotFoundException.class, () -> authorService.updateAuthor(new AuthorRequestDTO("name"),1));
        verify(authorRepository,times(1)).findById(1);
        verify(authorRepository,never()).save(any(Author.class));
    }


}
