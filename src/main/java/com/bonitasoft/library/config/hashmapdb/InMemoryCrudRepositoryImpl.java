package com.bonitasoft.library.config.hashmapdb;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.bonitasoft.library.collection.domain.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Component
public class InMemoryCrudRepositoryImpl implements BaseCrudRepository<Book, String> {

  private final HashMap<String, Book> database = new HashMap<String, Book>();

  @Override
  public Book save(String id, Book entity) {
    database.put(id, entity);
    return database.get(id);
  }

  @Override
  public Optional<Book> findById(String id) {
    return Optional.ofNullable(database.get(id));
  }

  @Override
  public ArrayList<Book> findAll() {
    ArrayList<Book> books = new ArrayList<>();
    for (String isbn : database.keySet()) {
      books.add(database.get(isbn));
    }
    return books;
  }

  @Override
  public void deleteById(String id) {
    database.remove(id);
  }

  @Override
  public void deleteAll() {
    database.clear();
  }

  @Override
  public List<Book> findByTitleOrAuthor(String title, String author) {
    List<Book> results = new ArrayList<>();

    for (Map.Entry<String, Book> entry : database.entrySet()) {
      Book book = entry.getValue();
      boolean matchesTitle = title != null && book.getTitle().toLowerCase().contains(title.toLowerCase());
      boolean matchesAuthor = author != null && book.getAuthor().toLowerCase().contains(author.toLowerCase());

      if (matchesTitle || matchesAuthor) {
        results.add(book);
      }
    }
    return results;
  }

}
