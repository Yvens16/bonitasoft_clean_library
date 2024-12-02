package com.bonitasoft.library.collection.usecases;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.exception.BookNotFoundException;

public class RetrieveOneBookUseCase {
  private CollectionRepository collectionRepository;

  public RetrieveOneBookUseCase(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  public Book execute(String isbn) {
    Book foundBook = this.collectionRepository.findByIsbn(isbn);
    if (foundBook == null) throw new BookNotFoundException(isbn);
    return foundBook;
  }
}
