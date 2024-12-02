package com.bonitasoft.library.borrowing.exception;

public class BookAlreadyBorrowedException extends RuntimeException {

  public BookAlreadyBorrowedException(String isbn) {
    super("This Book with the isbn: " + isbn + " is already borrowed");
  }

}
