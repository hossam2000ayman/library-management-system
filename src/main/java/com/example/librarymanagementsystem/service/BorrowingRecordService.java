package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.BorrowingRecord;

public interface BorrowingRecordService {
    BorrowingRecord borrowBookToPatron(Long patronId, Long bookId);

    BorrowingRecord returnBookFromPatron(Long patronId, Long bookId);
}
