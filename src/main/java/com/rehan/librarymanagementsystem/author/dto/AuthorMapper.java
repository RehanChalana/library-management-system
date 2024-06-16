package com.rehan.librarymanagementsystem.author.dto;

import com.rehan.librarymanagementsystem.author.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    AuthorResponseDTO authortoAuthorResponseDTO(Author author);
    Author authorRequestDTOToAuthor(AuthorRequestDTO authorResponseDTO);

}
