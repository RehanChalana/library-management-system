package com.rehan.librarymanagementsystem.service;

import com.rehan.librarymanagementsystem.model.Author;
import com.rehan.librarymanagementsystem.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

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
        return authorRepository.findById(id).get();
    }

    public void deleteById(int id) {
        authorRepository.deleteById(id);
    }

    public Author save(Author author) {
        return authorRepository.save(author);
    }
}