package com.rehan.librarymanagementsystem.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private long timeStamp;
}
