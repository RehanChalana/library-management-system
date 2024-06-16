package com.rehan.librarymanagementsystem.bookcopy;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
public interface BookCopyRepository extends CrudRepository<BookCopy,Integer> {
    List<BookCopy> findAll();
}
