package com.bonitasoft.library.collection.usecases;

import com.bonitasoft.library.collection.domain.Book;
import java.util.List;

public class SearchBooksByTitleOrAuthorUseCase {
  private CollectionRepository collectionRepository;

  public SearchBooksByTitleOrAuthorUseCase(CollectionRepository collectionRepository) {
    this.collectionRepository = collectionRepository;
  }

  public List<Book> execute(String title, String author) {
    return this.collectionRepository.findByTitleOrAuthor(title, author);
  }

}
