package com.example.librarymanagementsystem.service.serviceImplementation;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;

    /*helper method*/
    private Book findBookById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if (bookOptional.isEmpty())
            throw new BookNotFoundException("Book not found");
        return bookOptional.get();
    }
    /**/

    @Override
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        findBookById(id);
        bookRepository.deleteById(id);
    }


    @Override
    @CachePut(value = "books", key = "#id")
    public Book updateBook(Long id, Book newBook) {
        Book currentBook = findBookById(id);
        newBook.setId(id);
        if (!newBook.getTitle().isEmpty())
            currentBook.setTitle(newBook.getTitle());
        if (!newBook.getIsbn().isEmpty())
            currentBook.setIsbn(newBook.getIsbn());
        if (!newBook.getAuthor().isEmpty())
            currentBook.setAuthor(newBook.getAuthor());
        if (newBook.getPublication_year() != null)
            currentBook.setPublication_year(newBook.getPublication_year());
        bookRepository.saveAndFlush(currentBook);
        return currentBook;
    }

    @Override
    @Cacheable("books")
    public Book getBook(Long id) {
        return findBookById(id);
    }

    @Override
    @Cacheable("books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
