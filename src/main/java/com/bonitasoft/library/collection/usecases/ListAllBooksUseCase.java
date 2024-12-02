package com.bonitasoft.library.collection.usecases;

import java.util.List;

import com.bonitasoft.library.collection.domain.Book;

public class ListAllBooksUseCase {

  private CollectionRepository collectionRepository;

  public ListAllBooksUseCase(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  public List<Book> execute() {
    return this.collectionRepository.findAll();
  }
}
