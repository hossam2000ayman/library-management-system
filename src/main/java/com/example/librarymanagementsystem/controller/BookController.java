package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

//● Book management endpoints:
    //● GET /api/books: Retrieve a list of all books.
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    //● GET /api/books/{id}: Retrieve details of a specific book by ID.
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{id}")
    public Book getBookById(@PathVariable("id") Long id) {
        return bookService.getBook(id);
    }

    //● POST /api/books: Add a new book to the library.
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    //● PUT /api/books/{id}: Update an existing book's information.
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Book updateBook(@PathVariable("id") Long id, @RequestBody Book newBook) {
        return bookService.updateBook(id, newBook);
    }

    //● DELETE /api/books/{id}: Remove a book from the library.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book is deleted Successfully", HttpStatus.OK);
    }

}
