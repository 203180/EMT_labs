package com.example.labs.model.exceptions;


public class BookNotAvailableException extends RuntimeException{

    public BookNotAvailableException(String book) {
        super(String.format("%s is not available at the moment.",book));
    }
}
