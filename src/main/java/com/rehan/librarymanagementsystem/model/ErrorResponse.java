package com.rehan.librarymanagementsystem.model;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private long timeStamp;
}
