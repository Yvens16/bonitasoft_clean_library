package com.bonitasoft.library.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.config.hashmapdb.InMemoryCrudRepositoryImpl;
import com.bonitasoft.library.fixtures.BooksFixtures;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DatabaseTest {

  InMemoryCrudRepositoryImpl database = new InMemoryCrudRepositoryImpl();

  @BeforeAll
  public void setup() {
    List<Book> booksToSave = new BooksFixtures().listBookFixtures();
    for (Book book : booksToSave) {
      database.save(book.getIsbn(), book);
    }
  }

  @Test
  public void findById() {
    Optional<Book> optionalBool = database.findById("fake_isbn_1");
    assertEquals(true, optionalBool.isPresent());
  }

  @Test
  public void didNotFindById() {
    Optional<Book> optionalBool = database.findById("fake_isbn_5");
    assertEquals(false, optionalBool.isPresent());
  }

  @Test
  public void findByTitleOrAuthor() {
    List<Book> booksEmpty = database.findByTitleOrAuthor("Book 4", "Yvens Belaston");
    assertEquals(0, booksEmpty.size());

    List<Book> booksByAuthor = database.findByTitleOrAuthor(null, "Yvens");
    assertEquals(1, booksByAuthor.size());

    List<Book> booksByTitle = database.findByTitleOrAuthor("Book 1", null);
    assertEquals(1, booksByTitle.size());

    List<Book> booksByDifferentTitleAndAuthor = database.findByTitleOrAuthor("Book 1", "Yves");
    assertEquals(2, booksByDifferentTitleAndAuthor.size());

    List<Book> booksBySameTitleAndAuthor = database.findByTitleOrAuthor("Book 1", "Yvens");
    assertEquals(1, booksBySameTitleAndAuthor.size());
  }
}
