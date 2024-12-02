package com.bonitasoft.library.borrowing.usecases;

import com.bonitasoft.library.borrowing.exception.BookAlreadyReturnedException;
import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;

public class ReturnBorrowedBookUseCase {

  private BorrowingRepository borrowingRepository;

  public ReturnBorrowedBookUseCase(BorrowingRepository borrowingRepository) {
    this.borrowingRepository = borrowingRepository;
  };

  public Book execute(String isbn) {
    Book book = this.borrowingRepository.findByIsbn(isbn);

    if (book == null) {
      throw new BookNotFoundException(isbn);
    }

    if (book.getIsAvailable()) {
      throw new BookAlreadyReturnedException(isbn);
    }
    book.setIsAvailable(true);
    return this.borrowingRepository.updateBook(book);
  }

}
