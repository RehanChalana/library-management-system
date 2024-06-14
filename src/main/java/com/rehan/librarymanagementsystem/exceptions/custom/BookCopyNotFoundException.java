package com.rehan.librarymanagementsystem.exceptions.custom;

public class BookCopyNotFoundException extends RuntimeException{
    public BookCopyNotFoundException(String message) {
        super(message);
    }
}
