package com.rehan.librarymanagementsystem.bookcopy.dto;

import com.rehan.librarymanagementsystem.bookcopy.BookCopy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CopyMapper {

    @Mapping(source = "bookId" , target = "book.bookId")
    BookCopy RequestDTOtoCopy(CopyRequestDTO copyRequestDTO);

    @Mapping(source = "book.bookId" , target = "bookId")
    CopyResponseDTO CopyToResponseDTO(BookCopy bookCopy);
}
