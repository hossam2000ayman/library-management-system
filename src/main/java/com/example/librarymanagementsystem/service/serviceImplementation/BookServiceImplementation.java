package com.example.librarymanagementsystem.service.serviceImplementation;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImplementation implements BookService {
    private final BookRepository bookRepository;

    private final ModelMapper modelMapper;

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
    @CachePut(value = "books" , key = "#id")
    public Book updateBook(Long id, Book newBook) {
        Book currentBook = findBookById(id);
        newBook.setId(id);
        modelMapper.map(newBook, currentBook);
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
