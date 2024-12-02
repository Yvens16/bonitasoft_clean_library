package com.bonitasoft.library.usecases;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.AddNewBookUseCase;
import com.bonitasoft.library.collection.usecases.CollectionRepository;
import com.bonitasoft.library.collection.usecases.exception.BookAlreadyExistException;
import com.bonitasoft.library.collection.usecases.exception.PublishedYearInFutureException;
import com.bonitasoft.library.config.dependencies.DateProvider;

public class AddNewBookUsecaseTest {

  CollectionRepository mockCollectionRepository = mock(CollectionRepository.class);
  DateProvider mockDateProvider = mock(DateProvider.class);

  @Test
  public void raiseBookAlreadyExistException() {
    when(mockCollectionRepository.bookExist("fake_isbn")).thenReturn(true);
    when(mockDateProvider.getCurrentYear()).thenReturn(2024);
    Book mockBook = new Book("title", "author", "fake_isbn", 2012, true);
    AddNewBookUseCase useCase = new AddNewBookUseCase(mockCollectionRepository, mockDateProvider);
    assertThrows(BookAlreadyExistException.class, () -> useCase.execute(mockBook));
  }

  @Test
  public void createBook() {
    when(mockCollectionRepository.bookExist("fake_isbn")).thenReturn(false);
    when(mockDateProvider.getCurrentYear()).thenReturn(2024);
    Book mockBook = new Book("title", "author", "fake_isbn", 2012, true);
    when(mockCollectionRepository.saveNewBook(any(Book.class))).thenReturn(mockBook);

    AddNewBookUseCase useCase = new AddNewBookUseCase(mockCollectionRepository, mockDateProvider);
    Book savedBook = useCase.execute(mockBook);
    assertEquals("author", savedBook.getAuthor());
  }

  @Test
  public void publishYearMustBeInThePast() {
    int futurePublishYear = 2025;
    when(mockDateProvider.getCurrentYear()).thenReturn(2024);
    Book mockBook = new Book("title", "author", "fake_isbn", futurePublishYear, false);
    AddNewBookUseCase useCase = new AddNewBookUseCase(mockCollectionRepository, mockDateProvider);
    assertThrows(PublishedYearInFutureException.class, () -> useCase.execute(mockBook));
  }
}
