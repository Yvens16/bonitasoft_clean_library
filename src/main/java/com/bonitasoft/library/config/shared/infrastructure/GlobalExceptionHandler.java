package com.bonitasoft.library.config.shared.infrastructure;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bonitasoft.library.borrowing.exception.BookAlreadyBorrowedException;
import com.bonitasoft.library.borrowing.exception.BookAlreadyReturnedException;
import com.bonitasoft.library.collection.domain.exception.InvalidBookException;
import com.bonitasoft.library.collection.usecases.exception.BookAlreadyExistException;
import com.bonitasoft.library.collection.usecases.exception.BookAvailableException;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;
import com.bonitasoft.library.collection.usecases.exception.PublishedYearInFutureException;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(value = BookAlreadyExistException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(BookAlreadyExistException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(value = PublishedYearInFutureException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(PublishedYearInFutureException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(value = BookAvailableException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(BookAvailableException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(value = BookNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorResponse handleException(BookNotFoundException ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
  }

  @ExceptionHandler(value = BookAlreadyBorrowedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(BookAlreadyBorrowedException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(value = BookAlreadyReturnedException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(BookAlreadyReturnedException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

  @ExceptionHandler(value = InvalidBookException.class)
  @ResponseStatus(HttpStatus.CONFLICT)
  public @ResponseBody ErrorResponse handleException(InvalidBookException ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
  }

}