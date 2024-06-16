package com.rehan.librarymanagementsystem.author;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
    List<Author> findAll();
}
