package com.rehan.librarymanagementsystem.exceptions;

import com.rehan.librarymanagementsystem.exceptions.custom.AuthorNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.custom.BookCopyNotFoundException;
import com.rehan.librarymanagementsystem.exceptions.custom.BookNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandeller {
    @ExceptionHandler({AuthorNotFoundException.class, BookCopyNotFoundException.class, BookNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleException(Exception exc) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND);
        errorResponse.setMessage(exc.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }
}
