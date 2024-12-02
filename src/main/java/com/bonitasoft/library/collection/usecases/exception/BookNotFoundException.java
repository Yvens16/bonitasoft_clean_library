package com.bonitasoft.library.collection.usecases.exception;

public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(String isbn) {
    super("The book with the isbn " + isbn + " was not found");
  }
}
