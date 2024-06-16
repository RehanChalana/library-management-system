package com.rehan.librarymanagementsystem.bookcopy;

import com.rehan.librarymanagementsystem.book.BookRepository;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyMapper;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyRequestDTO;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyResponseDTO;
import com.rehan.librarymanagementsystem.exceptions.custom.BookCopyNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCopyService {

    private final CopyMapper copyMapper;

    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;

    public BookCopyService(BookCopyRepository bookCopyRepository,BookRepository bookRepository,CopyMapper copyMapper) {
        this.bookCopyRepository=bookCopyRepository;
        this.bookRepository=bookRepository;
        this.copyMapper=copyMapper;
    }

    public List<CopyResponseDTO> findAll(){
        List<BookCopy> copies = bookCopyRepository.findAll();
        return copies.stream().map(copyMapper::CopyToResponseDTO).collect(Collectors.toList());
    }

    public CopyResponseDTO findById(int id){
        BookCopy bookCopy = bookCopyRepository.findById(id).orElseThrow( () -> new BookCopyNotFoundException("book copy with copyId : "+id+" does not exists"));
        return copyMapper.CopyToResponseDTO(bookCopy);
    }

    public CopyResponseDTO addNewCopy(CopyRequestDTO bookCopy) {
        int bookId = bookCopy.bookId();
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+bookId+" does not exists"));
        BookCopy copyEntity = copyMapper.RequestDTOtoCopy(bookCopy);
        BookCopy savedEntity = bookCopyRepository.save(copyEntity);
        return copyMapper.CopyToResponseDTO(savedEntity);
    }

    public CopyResponseDTO updateCopy(CopyRequestDTO bookCopy,int copyId) {
        int bookId = bookCopy.bookId();
        bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book with bookId : "+bookId+" does not exists"));
        bookCopyRepository.findById(copyId).orElseThrow( () -> new BookCopyNotFoundException("book copy with copyId : "+copyId+" does not exists"));
        BookCopy copyEntity = copyMapper.RequestDTOtoCopy(bookCopy);
        BookCopy savedEntity =  bookCopyRepository.save(copyEntity);
        return copyMapper.CopyToResponseDTO(savedEntity);
    }

    public void deleteCopy(int copyId) {
        bookCopyRepository.findById(copyId).orElseThrow(() -> new BookCopyNotFoundException("book copy with copyId : "+copyId+" does not exists"));
        bookCopyRepository.deleteById(copyId);
    }

}
