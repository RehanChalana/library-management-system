package com.rehan.librarymanagementsystem.author;

import com.rehan.librarymanagementsystem.author.dto.AuthorRequestDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorResponseDTO;
import com.rehan.librarymanagementsystem.author.dto.AuthorMapper;
import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    public AuthorService(AuthorRepository authorRepository,AuthorMapper authorMapper) {
        this.authorMapper=authorMapper;
        this.authorRepository = authorRepository;
    }


    public List<AuthorResponseDTO> findAll() {
        List<Author> authors = authorRepository.findAll();
        return authors.stream().map(authorMapper::authortoAuthorResponseDTO).collect(Collectors.toList());
    }

    public AuthorResponseDTO findById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with authorId : " + id  +" does not exists"));
        return authorMapper.authortoAuthorResponseDTO(author);
    }

    public void deleteById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with authorId : " + id  +" does not exists"));
        authorRepository.deleteById(id);
    }

    public AuthorResponseDTO addNewAuthor(AuthorRequestDTO request) {
        Author authorEntity = authorMapper.authorRequestDTOToAuthor(request);
//        setting authorId to 0 because new entry is being created
        authorEntity.setAuthorId(0);
        Author savedEntity =  authorRepository.save(authorEntity);
        return authorMapper.authortoAuthorResponseDTO(savedEntity);
    }

    public AuthorResponseDTO updateAuthor(AuthorRequestDTO author,int authorId) {
        authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException("Author with authorId : " + authorId  +" does not exists"));
        Author authorEntity = authorMapper.authorRequestDTOToAuthor(author);
        authorEntity.setAuthorId(authorId);
        Author savedEntity =  authorRepository.save(authorEntity);
        return authorMapper.authortoAuthorResponseDTO(savedEntity);
    }
}