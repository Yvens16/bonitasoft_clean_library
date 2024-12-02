package com.bonitasoft.library.borrowing.infrastructure.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bonitasoft.library.borrowing.infrastructure.database.BorrowingRepositoryAdapter;
import com.bonitasoft.library.borrowing.usecases.BorrowOneBookUseCase;
import com.bonitasoft.library.borrowing.usecases.ReturnBorrowedBookUseCase;
import com.bonitasoft.library.collection.domain.Book;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("books")
public class BorrowingController {

  private BorrowingRepositoryAdapter borrowingRepository;

  public BorrowingController(BorrowingRepositoryAdapter borrowingRepository) {
    this.borrowingRepository = borrowingRepository;
  }

  @PutMapping("/{isbn}/borrow")
  public ResponseEntity<Book> borrowBook(@PathVariable String isbn) {
    BorrowOneBookUseCase useCase = new BorrowOneBookUseCase(this.borrowingRepository);
    return ResponseEntity.ok(useCase.execute(isbn));
  }

  @PutMapping("/{isbn}/return")
  public ResponseEntity<Book> returnBook(@PathVariable String isbn) {
    ReturnBorrowedBookUseCase useCase = new ReturnBorrowedBookUseCase(this.borrowingRepository);
    return ResponseEntity.ok(useCase.execute(isbn));
  }
}
