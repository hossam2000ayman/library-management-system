package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Book;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BookService {

    Book addBook(Book book);

    void deleteBook(Long id);

    Book updateBook(Long id, Book newBook);

    Book getBook(Long id);

    List<Book> getAllBooks();


}
