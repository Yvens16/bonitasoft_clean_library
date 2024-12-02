package com.bonitasoft.library.collection.infrastructure.database;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.usecases.CollectionRepository;
import com.bonitasoft.library.config.hashmapdb.InMemoryCrudRepositoryImpl;

@Repository
public class CollectionRepositoryAdapter implements CollectionRepository {
  private InMemoryCrudRepositoryImpl inMemoryCrudRepository;

  public CollectionRepositoryAdapter(InMemoryCrudRepositoryImpl inMemoryCrudRepository) {
    this.inMemoryCrudRepository = inMemoryCrudRepository;
  }

  @Override
  public Book saveNewBook(Book book) {
    return (Book) this.inMemoryCrudRepository.save(book.getIsbn(), book);
  }

  @Override
  public boolean bookExist(String isbn) {
    Optional<Book> optionalBook = this.inMemoryCrudRepository.findById(isbn);
    if (!optionalBook.isPresent()) {
      return false;
    }

    return true;
  }

  @Override
  public List<Book> findAll() {
    List<Book> bookList = this.inMemoryCrudRepository.findAll();
    return bookList;
  }

  @Override
  public Book findByIsbn(String isbn) {
    Optional<Book> optionalBook =  this.inMemoryCrudRepository.findById(isbn);
    if (!optionalBook.isPresent()) return null;
    Book bookData = optionalBook.get();
    return bookData;
  }

  @Override
  public List<Book> findByTitleOrAuthor(String title, String author) {
    return this.inMemoryCrudRepository.findByTitleOrAuthor(title, author);
  }

}
