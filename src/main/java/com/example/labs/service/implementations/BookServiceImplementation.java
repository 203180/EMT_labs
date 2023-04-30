package com.example.labs.service.implementations;

import com.example.labs.model.Author;
import com.example.labs.model.Book;
import com.example.labs.model.dtos.BookDto;
import com.example.labs.model.enumerations.Category;
import com.example.labs.model.exceptions.AuthorNotFoundException;
import com.example.labs.model.exceptions.BookNotAvailableException;
import com.example.labs.model.exceptions.BookNotFoundException;
import com.example.labs.repository.AuthorRepository;
import com.example.labs.repository.BookRepository;
import com.example.labs.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImplementation implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    public BookServiceImplementation(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> listAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Optional<Book> findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> save(BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthorId()));
        Book book = new Book(bookDto.getName(),bookDto.getCategory(),author,bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public Optional<Book> edit(Long bookId, BookDto bookDto) {
        Book book = bookRepository.findById(bookId).orElseThrow(()->new BookNotFoundException(bookId));
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        book.setAuthor(author);
        book.setAvailableCopies(bookDto.getAvailableCopies());
        bookRepository.save(book);
        return Optional.of(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Optional<Book> rent(Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
        Integer copies = book.getAvailableCopies();
            if(copies>0)
                book.setAvailableCopies(copies-1);
            else
                throw new BookNotAvailableException(book.getName());
        bookRepository.save(book);
        return Optional.of(book);
    }
}
