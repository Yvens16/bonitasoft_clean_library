package com.bonitasoft.library.borrowing.infrastructure.database;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bonitasoft.library.borrowing.usecases.BorrowingRepository;
import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.config.hashmapdb.InMemoryCrudRepositoryImpl;

@Repository
public class BorrowingRepositoryAdapter implements BorrowingRepository {

  private InMemoryCrudRepositoryImpl database;

  public BorrowingRepositoryAdapter(InMemoryCrudRepositoryImpl database) {
    this.database = database;
  }

  @Override
  public Book findByIsbn(String isbn) {
    Optional<Book> optionalBook = this.database.findById(isbn);
    if (!optionalBook.isPresent())
      return null;

    return optionalBook.get();
  }

  @Override
  public Book updateBook(Book book) {
    return this.database.save(book.getIsbn(), book);
  }
}
