package com.rehan.librarymanagementsystem.exceptions.custom;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(String message) {
        super(message);
    }
}
