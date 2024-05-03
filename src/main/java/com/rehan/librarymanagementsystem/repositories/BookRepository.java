package com.rehan.librarymanagementsystem.repositories;

import com.rehan.librarymanagementsystem.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
}
