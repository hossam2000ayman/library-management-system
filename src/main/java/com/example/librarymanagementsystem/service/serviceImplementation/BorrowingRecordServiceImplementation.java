package com.example.librarymanagementsystem.service.serviceImplementation;

import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exception.BookNotFoundException;
import com.example.librarymanagementsystem.exception.PatronNotFoundException;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.repository.BorrowingRecordRepository;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowingRecordServiceImplementation implements BorrowingRecordService {
    private final BorrowingRecordRepository borrowingRecordRepository;
    private final PatronRepository patronRepository;
    private final BookRepository bookRepository;


    @Override
    @Transactional
    public BorrowingRecord borrowBookToPatron(Long patronId, Long bookId) {
        Optional<Patron> patronOptional = patronRepository.findById(patronId);
        if (patronOptional.isEmpty()) throw new PatronNotFoundException("Patron not found");

        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty()) throw new BookNotFoundException("Book not found");

        BorrowingRecord borrowingRecord = new BorrowingRecord();
        borrowingRecord.setId(borrowingRecordRepository.getMaxId() + 1);
        borrowingRecord.setBorrowDate(Date.from(Instant.now()));
        borrowingRecord.setPatron(patronOptional.get());
        borrowingRecord.setBook(bookOptional.get());
        return borrowingRecordRepository.save(borrowingRecord);
    }

    @Override
    @Transactional
    public BorrowingRecord returnBookFromPatron(Long patronId, Long bookId) {
        Optional<BorrowingRecord> borrowingRecordOptional = borrowingRecordRepository.findByPatron_IdAndBook_Id(patronId, bookId);
        if (borrowingRecordOptional.isEmpty())
            throw new EntityNotFoundException("No Book " + bookId + " are borrow from Patron " + patronId + "  to return it");
        BorrowingRecord borrowingRecord = borrowingRecordOptional.get();
        borrowingRecord.setReturnDate(Date.from(Instant.now()));
        return borrowingRecordRepository.saveAndFlush(borrowingRecord);
    }
}
