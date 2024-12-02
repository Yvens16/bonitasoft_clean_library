package com.bonitasoft.library.config.hashmapdb;

import java.util.List;
import java.util.Optional;

public interface BaseCrudRepository<T, ID> {
  T save(String isbn, T entity);

  Optional<T> findById(String id);

  List<T> findAll();

  List<T> findByTitleOrAuthor(String title, String author);

  void deleteById(String id);

  void deleteAll();
}