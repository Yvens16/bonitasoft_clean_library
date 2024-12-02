package com.bonitasoft.library.borrowing.usecases;

import com.bonitasoft.library.borrowing.exception.BookAlreadyBorrowedException;
import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;

public class BorrowOneBookUseCase {

  private BorrowingRepository borrowingRepository;

  public BorrowOneBookUseCase(BorrowingRepository borrowingRepository) {
    this.borrowingRepository = borrowingRepository;
  };

  public Book execute(String isbn) {
    Book book = this.borrowingRepository.findByIsbn(isbn);

    if (book == null) {
      throw new BookNotFoundException(isbn);
    }

    if (!book.getIsAvailable()) {
      throw new BookAlreadyBorrowedException(isbn);
    }

    book.setIsAvailable(false);
    return this.borrowingRepository.updateBook(book);
  }

}
