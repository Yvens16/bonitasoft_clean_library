package com.bonitasoft.library.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

import com.bonitasoft.library.borrowing.exception.BookAlreadyBorrowedException;
import com.bonitasoft.library.borrowing.usecases.BorrowOneBookUseCase;
import com.bonitasoft.library.borrowing.usecases.BorrowingRepository;
import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;
import com.bonitasoft.library.fixtures.BooksFixtures;

public class BorrowOneBookUseCaseTest {
  BorrowingRepository mockBorrowingRepository = mock(BorrowingRepository.class);
  BooksFixtures bookFixtures = new BooksFixtures();


  @Test
  public void borrowOneBook() {
    Book bookToSave = bookFixtures.getAvailableBook();
    when(mockBorrowingRepository.findByIsbn(bookToSave.getIsbn())).thenReturn(bookToSave);
    when(mockBorrowingRepository.updateBook(bookToSave)).thenReturn(bookToSave);
    BorrowOneBookUseCase useCase = new BorrowOneBookUseCase(mockBorrowingRepository);
    Book borrowedBook = useCase.execute(bookToSave.getIsbn());
    assertEquals(false, borrowedBook.getIsAvailable());
  }

  @Test
  public void NoBookFound() {
    Book bookToSave = bookFixtures.getAvailableBook();
    when(mockBorrowingRepository.findByIsbn(bookToSave.getIsbn())).thenReturn(null);
    BorrowOneBookUseCase useCase = new BorrowOneBookUseCase(mockBorrowingRepository);
    assertThrows(BookNotFoundException.class, () -> useCase.execute(bookToSave.getIsbn()));
  }

  @Test
  public void BookNotAvailable() {
    Book bookToSave = bookFixtures.getNotAvailableBook();
    when(mockBorrowingRepository.findByIsbn(bookToSave.getIsbn())).thenReturn(bookToSave);
    BorrowOneBookUseCase useCase = new BorrowOneBookUseCase(mockBorrowingRepository);
    assertThrows(BookAlreadyBorrowedException.class, () -> useCase.execute(bookToSave.getIsbn()));
  }
}
