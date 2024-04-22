package com.example.librarymanagementsystem.exception.responses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@SuperBuilder
public class ErrorResponse {
    private int statusCode;
    private HttpStatus status;
    private String message;
}
