package com.bonitasoft.library.collection.domain;

import com.bonitasoft.library.collection.domain.exception.InvalidBookException;

public class Book {
  private String title;
  private String author;
  private String isbn;
  private Integer publishedYear;
  private Boolean isAvailable;

  public Book(String title,
      String author,
      String isbn,
      Integer publishedYear,
      Boolean isAvailable) {

    this.title = title;
    this.author = author;
    this.isbn = isbn;
    this.publishedYear = publishedYear;
    this.isAvailable = isAvailable;

    this.checkInvalidBookState();
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public String getIsbn() {
    return isbn;
  }

  public Integer getPublishedYear() {
    return publishedYear;
  }

  public Boolean getIsAvailable() {
    return isAvailable;
  }

  public void setIsAvailable(Boolean isAvailable) {
    this.isAvailable = isAvailable;
  }

  private void checkInvalidBookState() throws RuntimeException {
    if (title == null)
      throw new InvalidBookException("Book title must not be null");
    if (author == null)
      throw new InvalidBookException("Book author must not be null");
    if (isbn == null)
      throw new InvalidBookException("Book isbn must not be null");
    if (publishedYear == null)
      throw new InvalidBookException("Book publishedYear must not be null");
    if (isAvailable == null)
      throw new InvalidBookException("Book isAvailable must not be null");
  }

}
