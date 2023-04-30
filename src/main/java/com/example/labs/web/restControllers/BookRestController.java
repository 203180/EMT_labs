package com.example.labs.web.restControllers;

import com.example.labs.model.Book;
import com.example.labs.model.dtos.BookDto;
import com.example.labs.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> listBooks(){
        return bookService.listAll();
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> findById(@PathVariable Long bookId){
        return bookService.findById(bookId)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Book> save(BookDto bookDto){
        return bookService.save(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @PutMapping("/edit/{bookId}")
    public ResponseEntity<Book> edit(@PathVariable Long bookId, BookDto bookDto){
        return bookService.edit(bookId,bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        if(bookService.findById(id).isEmpty())
            return ResponseEntity.notFound().build();
        bookService.deleteById(id);
            if (bookService.findById(id).isPresent())
                return ResponseEntity.badRequest().build();
            return ResponseEntity.ok().build();
    }

    @GetMapping("/rent/{bookId}")
    public ResponseEntity rent(@PathVariable Long bookId){
        return bookService.rent(bookId)
                .map(book -> ResponseEntity.ok().build())
                .orElseGet(()->ResponseEntity.badRequest().build());
    }


}
