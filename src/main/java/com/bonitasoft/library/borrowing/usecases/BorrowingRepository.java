package com.bonitasoft.library.borrowing.usecases;

import com.bonitasoft.library.collection.domain.Book;

public interface BorrowingRepository {
  Book findByIsbn(String isbn);
  Book updateBook(Book book);
}
