package com.bonitasoft.library.collection.usecases.exception;

public class BookAlreadyExistException extends RuntimeException {

  public BookAlreadyExistException(String isbn) {
    super("This Book with the isbn: " + isbn + " Already exist");
  }

}
