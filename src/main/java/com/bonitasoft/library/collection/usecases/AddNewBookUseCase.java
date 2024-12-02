package com.bonitasoft.library.collection.usecases;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookAlreadyExistException;
import com.bonitasoft.library.collection.usecases.exception.PublishedYearInFutureException;
import com.bonitasoft.library.config.dependencies.DateProvider;

public class AddNewBookUseCase {

  private CollectionRepository collectionRepository;
  private DateProvider dateProvider;

  public AddNewBookUseCase(CollectionRepository collectionRepository, DateProvider dateProvider) {
    this.collectionRepository = collectionRepository;
    this.dateProvider = dateProvider;
  }

  public Book execute(Book newBook) {
    int currentYear = this.dateProvider.getCurrentYear();
    if (currentYear < newBook.getPublishedYear()) {
      throw new PublishedYearInFutureException(newBook.getPublishedYear());
    }
    if (this.collectionRepository.bookExist(newBook.getIsbn())) {
      throw new BookAlreadyExistException(newBook.getIsbn());
    }
    Book saveBook = this.collectionRepository.saveNewBook(newBook);
    return saveBook;
  }
}
