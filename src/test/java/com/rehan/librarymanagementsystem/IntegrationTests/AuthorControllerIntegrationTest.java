package com.rehan.librarymanagementsystem.IntegrationTests;

import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,scripts = {"/schema.sql","/data.sql"})
@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,scripts = "/clear.sql")

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerIntegrationTest {

    @LocalServerPort
    private Integer port;

    @Autowired
    private TestRestTemplate restTemplate;

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @BeforeAll
    static void beforeAll() {
        postgres.start();
    }


    @AfterAll
    static void afterAll() {
        postgres.stop();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",postgres::getJdbcUrl);
        registry.add("spring.datasource.username",postgres::getUsername);
        registry.add("spring.datasource.password",postgres::getPassword);
    }



    @Test
    public void findByIdSuccessTest() {
        String url = "http://localhost:" + port + "/api/authors/1";
        ResponseEntity<AuthorResponseDTO> response = restTemplate.getForEntity(url,AuthorResponseDTO.class);
        AuthorResponseDTO result = response.getBody();
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertNotNull(result);
        assertEquals(1,result.authorId());
        assertEquals("George Orwell",result.name());
    }

    @Test
    public void findByIdNotFoundTest() {
        String url = "http://localhost:" + port + "/api/authors/10";
        ResponseEntity<AuthorResponseDTO> response = restTemplate.getForEntity(url,AuthorResponseDTO.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void deleteByIdSuccessTest() {
        String url = "http://localhost:" + port + "/api/authors/6";
        ResponseEntity<?> response =  restTemplate.exchange(url,HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        assertEquals(response.getStatusCode(),HttpStatus.OK);
    }

    @Test
    public void findAllTest() {
        String url = "http://localhost:" + port + "/api/authors";
        ResponseEntity<List<AuthorResponseDTO>> response = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<AuthorResponseDTO>>() {
        });
        List<AuthorResponseDTO> responseDTOS = response.getBody();
        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertNotNull(responseDTOS);
        assertEquals(6,responseDTOS.size());
    }

    @Test
    public void deleteByIdNotFoundTest() {
        String url = "http://localhost:" + port + "/api/authors/10";
        ResponseEntity<?> response =  restTemplate.exchange(url,HttpMethod.DELETE, HttpEntity.EMPTY,Void.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

    @Test
    public void saveNewAuthorTest() {
        String url = "http://localhost:" + port + "/api/authors";
        AuthorRequestDTO request = new AuthorRequestDTO("Marilyn Jain");
        ResponseEntity<AuthorResponseDTO> response = restTemplate.postForEntity(url,request,AuthorResponseDTO.class);
        AuthorResponseDTO resultDTO = response.getBody();
        assertEquals(response.getStatusCode(),HttpStatus.CREATED);
        assertNotNull(resultDTO);
        assertEquals(7,resultDTO.authorId());
        assertEquals("Marilyn Jain",resultDTO.name());
    }

    @Test
    public void updateAuthorSuccessTest() {
        String url = "http://localhost:" + port + "/api/authors/6";
        AuthorRequestDTO requestDTO = new AuthorRequestDTO("changed Name");
        HttpEntity<AuthorRequestDTO> request = new HttpEntity<>(requestDTO);
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange(url,HttpMethod.PUT,request,AuthorResponseDTO.class);
        AuthorResponseDTO responseDTO = response.getBody();

        assertEquals(response.getStatusCode(),HttpStatus.OK);
        assertNotNull(responseDTO);
        assertEquals(6,responseDTO.authorId());
        assertEquals("changed Name",responseDTO.name());
    }

    @Test
    public void updateAuthorNotFoundTest() {
        String url = "http://localhost:" + port + "/api/authors/10";
        AuthorRequestDTO requestDTO = new AuthorRequestDTO("changed Name");
        HttpEntity<AuthorRequestDTO> request = new HttpEntity<>(requestDTO);
        ResponseEntity<AuthorResponseDTO> response = restTemplate.exchange(url,HttpMethod.PUT,request,AuthorResponseDTO.class);
        assertEquals(response.getStatusCode(),HttpStatus.NOT_FOUND);
    }

}
