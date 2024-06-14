package com.rehan.librarymanagementsystem.book;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {
    List<Book> findAll();
}
