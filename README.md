# bonitasoft_clean_library

## Run the Project

```
mvn spring-boot:run
```

## Launch project unit tests

```
mvn test
```

## API documentation

- Run the project
- Navigate to: http://localhost:8080/swagger-ui/index.html
- There you will see the openApi definition of the API and test the requests

## Assumptions

- Features

  - An isbn is supposed to be a unique identifier for a book and it copies, but we assume here that it is a bar code and don't introduce the concept of copies. It shall be treated as a unique identifier in the database.
  - We assume that a book cannot have a pre-released version. So the published year must be in the past or equal to the current year.
  - For the search feature, we assume that if one of the two parameters (title, author) match, it shall return the book in the list of results.
  - We assume that we can search by title or author and by title and author sending back all the possible reuslts.

- Architecture
  - I decided to go for a clean and screaming achitecture, making the code portable and easily testable.
  - Why screaming, because it is structure by modules collection for the managment of the books by admin users and borrowing for non admin users of the library.
  - Why clean architecture, because every layer is seprated started from the domain which contains the business objects. The usecases which contains the application code to handle actions by users. To the infrastructure which contains the framework code (controllers).
  - Using Dependency Injection, we make sure that the framework depends on the usecases which depend on the domain. Thus making our code easily testable, easily portable.
  - I should mention that I took some shortcuts for example by not implementing a Presenter layer with (mappers and dto) to convert the data.
