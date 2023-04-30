package com.example.labs.service.implementations;

import com.example.labs.model.Author;
import com.example.labs.model.Country;
import com.example.labs.model.dtos.AuthorDto;
import com.example.labs.model.exceptions.CountryNotFoundException;
import com.example.labs.repository.AuthorRepository;
import com.example.labs.repository.CountryRepository;
import com.example.labs.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImplementation implements AuthorService {

    private final AuthorRepository authorRepository;

    private final CountryRepository countryRepository;

    public AuthorServiceImplementation(AuthorRepository authorRepository, CountryRepository countryRepository) {
        this.authorRepository = authorRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Optional<Author> save(AuthorDto authorDto) {
        Country country = countryRepository.findById(authorDto.getCountryId()).orElseThrow(()->new CountryNotFoundException(authorDto.getCountryId()));
        Author author = new Author(authorDto.getName(),authorDto.getSurname(),country);
        authorRepository.save(author);
        return Optional.of(author);
    }

    @Override
    public void deleteById(Long id) {
        authorRepository.deleteById(id);
    }
}
