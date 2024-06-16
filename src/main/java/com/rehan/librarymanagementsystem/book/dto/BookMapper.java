package com.rehan.librarymanagementsystem.book.dto;

import com.rehan.librarymanagementsystem.book.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "authorId",target = "author.authorId")
    Book requestDTOtoBook(BookRequestDTO bookRequestDTO);

    @Mapping(source = "author.authorId",target = "authorId")
    BookResponseDTO bookToResponseDTO(Book book);
}
