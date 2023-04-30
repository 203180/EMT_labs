package com.example.labs.service;

import com.example.labs.model.Author;
import com.example.labs.model.dtos.AuthorDto;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> listAll();

    Optional<Author> findById(Long id);

    Optional<Author> save(AuthorDto authorDto);

    void deleteById(Long id);
}
