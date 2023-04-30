package com.example.labs.service;

import com.example.labs.model.Book;
import com.example.labs.model.dtos.BookDto;
import com.example.labs.model.enumerations.Category;

import java.util.List;
import java.util.Optional;

public interface BookService {

    List<Book> listAll();

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    Optional<Book> save(BookDto bookDto);

    Optional<Book> edit(Long bookId, BookDto bookDto);

    void deleteById(Long id);

    Optional<Book> rent(Long id);
}
