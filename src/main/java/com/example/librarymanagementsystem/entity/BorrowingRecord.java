package com.example.librarymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "borrowing_records")
@Setter
@Getter
@Validated
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    @Column(name = "borrow_date")
    private Date borrowDate;

    @JsonFormat(pattern = "yyyy-MM-dd' 'HH:mm:ss")
    @Column(name = "return_date")
    private Date returnDate;


}
