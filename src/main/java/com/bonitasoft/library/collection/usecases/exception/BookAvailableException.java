package com.bonitasoft.library.collection.usecases.exception;

public class BookAvailableException extends RuntimeException {
  public BookAvailableException() {
    super("The book must be available");
  }
}
