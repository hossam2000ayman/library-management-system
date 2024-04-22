package com.example.librarymanagementsystem.exception.handler;


import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.PatronNotFoundException;
import com.example.librarymanagementsystem.exception.responses.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class Handler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ex.printStackTrace();

        return ResponseEntity.badRequest().body(ErrorResponse.builder().statusCode(BAD_REQUEST.value()).status(BAD_REQUEST).message("INPUT DATA IS NOT VALID.").build());

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ex.printStackTrace();


        return ResponseEntity.badRequest().body(ErrorResponse.builder().statusCode(BAD_REQUEST.value()).status(BAD_REQUEST).message("Type mismatch. Check request data format.").build());
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorResponse> handleSQLException(SQLException ex) {

        return ResponseEntity.badRequest().body(ErrorResponse.builder().statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value()).status(HttpStatus.INTERNAL_SERVER_ERROR).message("Query: " + ex.getSQLState() + "\n First: " + ex.getMessage() + "\n Second: " + ex.getNextException().getMessage()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ex.printStackTrace();

        return ResponseEntity.badRequest().body(ErrorResponse.builder().statusCode(BAD_REQUEST.value()).status(BAD_REQUEST).message(ex.getMessage()).build());
    }

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundException(BookNotFoundException bookNotFoundException) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(bookNotFoundException.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }


    @ExceptionHandler(PatronNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePatronNotFoundException(PatronNotFoundException patronNotFoundException) {
        ErrorResponse errorResponse = ErrorResponse.builder().message(patronNotFoundException.getMessage()).statusCode(HttpStatus.NOT_FOUND.value()).status(HttpStatus.NOT_FOUND).build();

        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }
}
