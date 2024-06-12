package com.rehan.librarymanagementsystem.exceptions;

public class BookCopyNotFoundException extends RuntimeException{
    public BookCopyNotFoundException(String message) {
        super(message);
    }
}
