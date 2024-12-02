package com.bonitasoft.library.collection.usecases;

import java.util.List;

import com.bonitasoft.library.collection.domain.Book;

public interface CollectionRepository {
  public Book saveNewBook(Book book);

  public boolean bookExist(String isbn);

  public List<Book> findAll();

  public Book findByIsbn(String isbn);

  public List<Book> findByTitleOrAuthor(String title, String author);
}
