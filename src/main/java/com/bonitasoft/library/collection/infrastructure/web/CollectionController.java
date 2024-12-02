package com.bonitasoft.library.collection.infrastructure.web;

import org.springframework.web.bind.annotation.RestController;

import com.bonitasoft.library.collection.domain.Book;
import com.bonitasoft.library.collection.infrastructure.database.CollectionRepositoryAdapter;
import com.bonitasoft.library.collection.usecases.AddNewBookUseCase;
import com.bonitasoft.library.collection.usecases.ListAllBooksUseCase;
import com.bonitasoft.library.collection.usecases.RetrieveOneBookUseCase;
import com.bonitasoft.library.collection.usecases.SearchBooksByTitleOrAuthorUseCase;
import com.bonitasoft.library.config.dependencies.DateProvider;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController()
@RequestMapping("books")
public class CollectionController {

  private CollectionRepositoryAdapter collectionRepository;
  private DateProvider dateProvider;

  public CollectionController(CollectionRepositoryAdapter collectionRepository, DateProvider dateProvider) {
    this.collectionRepository = collectionRepository;
    this.dateProvider = dateProvider;
  }

  @PostMapping
  public Book addNewBook(@RequestBody Book bookRequest) {
    AddNewBookUseCase useCase = new AddNewBookUseCase(this.collectionRepository, this.dateProvider);
    return useCase.execute(bookRequest);
  }

  @GetMapping
  public List<Book> ListBooks() {
    ListAllBooksUseCase useCase = new ListAllBooksUseCase(this.collectionRepository);
    return useCase.execute();
  }

  @GetMapping("/{isbn}")
  public Book getBooK(@PathVariable String isbn) {
    RetrieveOneBookUseCase useCase = new RetrieveOneBookUseCase(this.collectionRepository);
    return useCase.execute(isbn);
  }

  @GetMapping("/search_by_author_title")
  public List<Book> searchByAuthorOrTitle(@RequestParam(required = false) String title, @RequestParam(required = false) String author) {
    SearchBooksByTitleOrAuthorUseCase useCase = new SearchBooksByTitleOrAuthorUseCase(this.collectionRepository);
      return useCase.execute(title, author);
  }
  

}
