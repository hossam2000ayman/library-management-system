package com.example.librarymanagementsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Setter
@Getter
@Validated
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @NotBlank(message = "title must not be blank")
    @Column(name = "title",unique = true,nullable = false)
    private String title;

    @NonNull
    @NotBlank(message = "author must not be blank")
    @Column(name = "author",nullable = false)
    private String author;

    @Column(name = "publication_year")
    private int publication_year;

    @NonNull
    @NotBlank(message = "isbn must not be blank")
    @Column(name = "isbn", unique = true, nullable = false)
    private String isbn;


}
