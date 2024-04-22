package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/patrons")
@RequiredArgsConstructor
public class PatronController {
    private final PatronService patronService;

//● Patron management endpoints:
    //● GET /api/patrons: Retrieve a list of all patrons.
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping
    public List<Patron> getAllPatrons() {
        return patronService.getAllPatron();
    }

    //● GET /api/patrons/{id}: Retrieve details of a specific patron by ID.
    @ResponseStatus(HttpStatus.FOUND)
    @GetMapping("{id}")
    public Patron getPatronById(@PathVariable("id") Long id) {
        return patronService.getPatronById(id);
    }

    //● POST /api/patrons: Add a new patron to the system.
    @ResponseStatus(HttpStatus.ACCEPTED)
    @PostMapping
    public Patron addPatron(@RequestBody Patron patron) {
        return patronService.addPatron(patron);
    }

    //● PUT /api/patrons/{id}: Update an existing patron's information.
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("{id}")
    public Patron updatePatron(@PathVariable("id") Long id, @RequestBody Patron newPatron) {
        return patronService.updatePatron(id, newPatron);
    }

    //● DELETE /api/patrons/{id}: Remove a patron from the system.
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePatron(@PathVariable("id") Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>("Patron deleted successfully", HttpStatus.OK);
    }

}
