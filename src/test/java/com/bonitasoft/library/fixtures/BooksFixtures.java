package com.bonitasoft.library.fixtures;

import java.util.List;

import com.bonitasoft.library.collection.domain.Book;

public class BooksFixtures {

  public List<Book> listBookFixtures() {
    Book book1 = new Book("Book 1", "Yvens", "fake_isbn_1", 2012, false);
    Book book2 = new Book("Book 2", "Yves", "fake_isbn_2", 2001, false);
    Book book3 = new Book("Book 3", "Belaston", "fake_isbn_3", 2015, false);
    return List.of(book1, book2, book3);
  }

  public Book getAvailableBook() {
    Book book = new Book("Vailable Book", "Belaston", "fake_isbn_4", 2015, true);
    return book;
  }

  public Book getNotAvailableBook() {
    Book book = new Book("Vailable Book", "Belaston", "fake_isbn_4", 2015, false);
    return book;
  }
}
