package com.bonitasoft.library.borrowing.exception;

public class BookAlreadyReturnedException extends RuntimeException {

  public BookAlreadyReturnedException(String isbn) {
    super("This Book with the isbn: " + isbn + " is already in the collection");
  }
}