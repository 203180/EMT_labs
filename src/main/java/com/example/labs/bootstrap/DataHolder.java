//package com.example.labs.bootstrap;
//
//import com.example.labs.model.Author;
//import com.example.labs.model.Book;
//import com.example.labs.model.Country;
//import com.example.labs.model.enumerations.Category;
//import jakarta.annotation.PostConstruct;
//import lombok.Getter;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//@Getter
//public class DataHolder {
//
//    public static List<Book> books = new ArrayList<>();
//
//    public static List<Author> authors = new ArrayList<>();
//
//    public static List<Country> countries = new ArrayList<>();
//
//    @PostConstruct
//    public void init() {
//        countries.add(new Country("MKD","EU"));
//        authors.add(new Author("author","1",countries.get(0)));
//        books.add(new Book("Book 1", Category.FANTASY,authors.get(0),10));
//    }
//}
