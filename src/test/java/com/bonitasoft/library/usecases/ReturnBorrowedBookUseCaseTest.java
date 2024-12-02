package com.bonitasoft.library.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import org.junit.jupiter.api.Test;

import com.bonitasoft.library.borrowing.exception.BookAlreadyReturnedException;
import com.bonitasoft.library.borrowing.usecases.BorrowingRepository;
import com.bonitasoft.library.borrowing.usecases.ReturnBorrowedBookUseCase;
import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;
import com.bonitasoft.library.fixtures.BooksFixtures;

public class ReturnBorrowedBookUseCaseTest {
  BorrowingRepository mockBorrowingRepository = mock(BorrowingRepository.class);
  BooksFixtures bookFixtures = new BooksFixtures();

  
  @Test
  public void returnOneBook() {
    Book bookToSave = bookFixtures.getNotAvailableBook();
    when(mockBorrowingRepository.findByIsbn(bookToSave.getIsbn())).thenReturn(bookToSave);
    when(mockBorrowingRepository.updateBook(bookToSave)).thenReturn(bookToSave);
    ReturnBorrowedBookUseCase useCase = new ReturnBorrowedBookUseCase(mockBorrowingRepository);
    Book borrowedBook = useCase.execute(bookToSave.getIsbn());
    assertEquals(true, borrowedBook.getIsAvailable());
  }

  @Test
  public void NoBookFound() {
    Book bookToSave = bookFixtures.getAvailableBook();
    when(mockBorrowingRepository.findByIsbn("not_found_isbn")).thenReturn(null);
    ReturnBorrowedBookUseCase useCase = new ReturnBorrowedBookUseCase(mockBorrowingRepository);
    assertThrows(BookNotFoundException.class, () -> useCase.execute(bookToSave.getIsbn()));
  }

  @Test
  public void BookAlreadyAvailable() {
    Book bookToSave = bookFixtures.getAvailableBook();
    when(mockBorrowingRepository.findByIsbn(bookToSave.getIsbn())).thenReturn(bookToSave);
    ReturnBorrowedBookUseCase useCase = new ReturnBorrowedBookUseCase(mockBorrowingRepository);
    assertThrows(BookAlreadyReturnedException.class, () -> useCase.execute(bookToSave.getIsbn()));
  }
}
