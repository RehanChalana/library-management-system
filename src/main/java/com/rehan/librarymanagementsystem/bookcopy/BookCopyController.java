package com.rehan.librarymanagementsystem.bookcopy;

import com.rehan.librarymanagementsystem.bookcopy.dto.CopyRequestDTO;
import com.rehan.librarymanagementsystem.bookcopy.dto.CopyResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookCopyController {
    private final BookCopyService bookCopyService;

    public BookCopyController(BookCopyService bookCopyService) {
        this.bookCopyService=bookCopyService;
    }

    @GetMapping("/bookcopies")
    public List<CopyResponseDTO> findALl(){
        return bookCopyService.findAll();
    }

    @GetMapping("/bookcopies/{copyId}")
    public CopyResponseDTO findById(@PathVariable int copyId) {
        return bookCopyService.findById(copyId);
    }

    @PostMapping("/bookcopies")
    public ResponseEntity<CopyResponseDTO> addNewCopy(@RequestBody @Valid CopyRequestDTO bookCopy){
        CopyResponseDTO createdBook =  bookCopyService.addNewCopy(bookCopy);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    @PutMapping("/bookcopies/{copyId}")
    public CopyResponseDTO update(@RequestBody @Valid  CopyRequestDTO bookCopy ,@PathVariable int copyId) {
        return bookCopyService.updateCopy(bookCopy,copyId);
    }

    @DeleteMapping("/bookcopies/{copyId}")
    public void delete(@PathVariable int copyId) {
         bookCopyService.deleteById(copyId);
    }


}
