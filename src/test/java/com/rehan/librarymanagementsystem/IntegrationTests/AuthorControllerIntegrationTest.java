package com.rehan.librarymanagementsystem.IntegrationTests;

import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = {"/schema.sql","/data.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "/clear.sql")

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");


    @Test
    public void findByIdSuccessTest() {
       ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange("/api/authors/1",HttpMethod.GET,HttpEntity.EMPTY,AuthorResponseDTO.class);
       AuthorResponseDTO responseBody = response.getBody();
       assertEquals(response.getStatusCode(),HttpStatus.OK);
       assertNotNull(responseBody);
       assertEquals("George Orwell",responseBody.name());
       assertEquals(1,responseBody.authorId());
    }

    @Test
    public void findByIdNotFoundTest() {
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange("/api/authors/-1",HttpMethod.GET,HttpEntity.EMPTY,AuthorResponseDTO.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteByIdSuccessTest() {
//        authorId :- 6 is not a foreign key in the books table so it can be deleted
        ResponseEntity<?> response =  restTemplate.exchange("/api/authors/6",HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        ResponseEntity<AuthorResponseDTO> verifyDeleteResponse = restTemplate.exchange("/api/authors/6",HttpMethod.GET,HttpEntity.EMPTY,AuthorResponseDTO.class);
        assertEquals(HttpStatus.NOT_FOUND,verifyDeleteResponse.getStatusCode());
    }

    @Test
    public void deleteByIdForeignKeyConstraintTest() {
//        the authorId :- 1 is foreign key in books table , so it can't be deleted
        ResponseEntity<?> response =  restTemplate.exchange("/api/authors/1",HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        assertEquals(response.getStatusCode(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void deleteByIdNotFoundTest() {
        ResponseEntity<?> response =  restTemplate.exchange("/api/authors/-1",HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }


    @Test
    public void findAllTest() {
        ResponseEntity<List<AuthorResponseDTO>> response = restTemplate.exchange("/api/authors", HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<AuthorResponseDTO>>() {});
        List<AuthorResponseDTO> responseDTOS = response.getBody();
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertNotNull(responseDTOS);
        assertEquals(6,responseDTOS.size());
    }



    @Test
    public void saveNewAuthorTest() {
        AuthorRequestDTO request = new AuthorRequestDTO("Marilyn Jain");
        HttpEntity<AuthorRequestDTO> requestEntity = new HttpEntity<>(request);
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange("/api/authors",HttpMethod.POST,requestEntity,AuthorResponseDTO.class);
        AuthorResponseDTO resultDTO = response.getBody();
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
        assertNotNull(resultDTO);
        assertTrue(resultDTO.authorId() > 0);
        assertEquals("Marilyn Jain",resultDTO.name());
    }

    @Test
    public void updateAuthorSuccessTest() {
        AuthorRequestDTO requestDTO = new AuthorRequestDTO("changed Name");
        HttpEntity<AuthorRequestDTO> request = new HttpEntity<>(requestDTO);
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange("/api/authors/6",HttpMethod.PUT,request,AuthorResponseDTO.class);
        AuthorResponseDTO responseDTO = response.getBody();

        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertNotNull(responseDTO);
        assertEquals(6,responseDTO.authorId());
        assertEquals("changed Name",responseDTO.name());
    }

    @Test
    public void updateAuthorNotFoundTest() {
        AuthorRequestDTO requestDTO = new AuthorRequestDTO("changed Name");
        HttpEntity<AuthorRequestDTO> request = new HttpEntity<>(requestDTO);
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange("/api/authors/10",HttpMethod.PUT,request,AuthorResponseDTO.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

}
