package com.rehan.librarymanagementsystem.exceptions.custom;

public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(String message) {
        super(message);
    }
}
