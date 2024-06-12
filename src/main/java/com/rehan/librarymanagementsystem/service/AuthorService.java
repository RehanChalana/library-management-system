package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.exceptions.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    public Iterable<Author> findAll() {
        return authorRepository.findAll();
    }

    public Author findById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) throw new AuthorNotFoundException("Author with authorId : " + id  +" does not exists");
        return author.get();
    }

    public void deleteById(int id) {
        Optional<Author> author = authorRepository.findById(id);
        if(author.isEmpty()) throw new AuthorNotFoundException("Author with authorId : " + id  +" does not exists");
        authorRepository.deleteById(id);
    }

    public Author addNewAuthor(Author author) {
        author.setAuthorId(0);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author) {
        int authorId = author.getAuthorId();
        if(authorRepository.findById(authorId).isEmpty()) throw new AuthorNotFoundException("Author with authorId : "+authorId+" does not exists");
        return authorRepository.save(author);
    }
}