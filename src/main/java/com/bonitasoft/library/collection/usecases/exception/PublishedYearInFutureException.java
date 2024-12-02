package com.bonitasoft.library.collection.usecases.exception;

public class PublishedYearInFutureException extends RuntimeException {

  public PublishedYearInFutureException(int publishedYear) {
    super("This Book has a published year in the future: " + "(" + publishedYear + ")");
  }

}
