package com.rehan.librarymanagementsystem.author;

import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
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
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with authorId : " + id  +" does not exists"));
        return author;
    }

    public void deleteById(int id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> new AuthorNotFoundException("Author with authorId : " + id  +" does not exists"));
        authorRepository.deleteById(id);
    }

    public Author addNewAuthor(Author author) {
        author.setAuthorId(0);
        return authorRepository.save(author);
    }

    public Author updateAuthor(Author author) {
        int authorId = author.getAuthorId();
        authorRepository.findById(authorId).orElseThrow(()-> new AuthorNotFoundException("Author with authorId : " + authorId  +" does not exists"));
        return authorRepository.save(author);
    }
}