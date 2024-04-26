package com.example.librarymanagementsystem.service.serviceImplementation;

import com.example.librarymanagementsystem.entity.Patron;
import com.example.librarymanagementsystem.exception.PatronNotFoundException;
import com.example.librarymanagementsystem.repository.PatronRepository;
import com.example.librarymanagementsystem.service.PatronService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PatronServiceImplementation implements PatronService {
    private final PatronRepository patronRepository;

    /*helper method*/
    private Patron findPatronById(Long id) {
        Optional<Patron> patronOptional = patronRepository.findById(id);
        if (patronOptional.isEmpty())
            throw new PatronNotFoundException("Book not found");
        return patronOptional.get();
    }
    /**/

    @Override
    @Cacheable(value = "patrons")
    public List<Patron> getAllPatron() {
        return patronRepository.findAll();
    }

    @Override
    @Cacheable(value = "patrons")
    public Patron getPatronById(Long id) {
        return findPatronById(id);
    }

    @Override
    public Patron addPatron(Patron patron) {
        return patronRepository.save(patron);
    }

    @Override
    @CachePut(value = "patrons", key = "#id")
    public Patron updatePatron(Long id, Patron newPatron) {
        Patron patron = findPatronById(id);
        newPatron.setId(id);
        if (!newPatron.getName().isEmpty())
            patron.setName(newPatron.getName());
        if (!newPatron.getContactInformation().getPhoneNumber().isEmpty()
                && newPatron.getContactInformation().getAge() != null
                && !newPatron.getContactInformation().getAddress().isEmpty()
                && !newPatron.getContactInformation().getLanguage().isEmpty()
                && !newPatron.getContactInformation().getNationality().isEmpty()
                && newPatron.getContactInformation().getPatron().getId() != null)
            patron.setContactInformation(newPatron.getContactInformation());
        patronRepository.saveAndFlush(patron);
        return patron;
    }

    @Override
    @CacheEvict(value = "patrons", key = "#id")
    public void deletePatron(Long id) {
        findPatronById(id);
        patronRepository.deleteById(id);
    }
}
