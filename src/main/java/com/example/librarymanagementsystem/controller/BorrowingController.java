package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.BorrowingRecord;
import com.example.librarymanagementsystem.service.BorrowingRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingRecordService borrowingRecordService;


    //● Borrowing endpoints:
//● POST /api/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
    @PostMapping("borrow/{bookId}/patron/{patronId}")
    public BorrowingRecord borrowBookToPatron(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        return borrowingRecordService.borrowBookToPatron(patronId,bookId);
    }

    //● PUT /api/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
    @PutMapping("return/{bookId}/patron/{patronId}")
    public BorrowingRecord returnBookFromPatron(@PathVariable("bookId") Long bookId, @PathVariable("patronId") Long patronId) {
        return borrowingRecordService.returnBookFromPatron(patronId,bookId);
    }
}
