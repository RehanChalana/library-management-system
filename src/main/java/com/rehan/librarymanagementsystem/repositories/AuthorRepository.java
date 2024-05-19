package com.rehan.librarymanagementsystem.repositories;

import com.rehan.librarymanagementsystem.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author,Integer> {
}
