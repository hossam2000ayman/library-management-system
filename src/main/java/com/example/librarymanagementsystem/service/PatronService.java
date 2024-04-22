package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.entity.Patron;

import java.util.List;

public interface PatronService {

    List<Patron> getAllPatron();

    Patron getPatronById(Long id);

    Patron addPatron(Patron patron);

    Patron updatePatron(Long id, Patron newPatron);

    void deletePatron(Long id);

}
