# Java Library Management System

A console-based Library Management System built in Java to practice
Object-Oriented design, the Collections Framework, exception handling,
the Stream API, JUnit testing, and JDBC persistence with PostgreSQL.

## Features

- Display all books
- Search a book by ISBN
- Search books by title (partial, case-insensitive)
- Add a book
- Delete a book
- Borrow a book
- Return a book
- Menu-driven console interface
- Persistent storage in a real PostgreSQL database

## Concepts Practiced

- Object-Oriented Programming (Encapsulation, Interfaces)
- Layered architecture (UI → Service → Repository)
- Custom unchecked exceptions (`InvalidBookException`)
- Collections (`List`, `Map`)
- Stream API (`filter`, `toList`)
- `Optional`
- Result-enum pattern for expected business outcomes
- JUnit 5 unit testing with hand-written test doubles
- JDBC: `Connection`, `PreparedStatement`, `ResultSet`
- Externalized configuration (`application.properties`, gitignored)
- Maven project structure and dependency management

## Project Structure

```text
src/
├── main/
│   ├── java/com/bhavesh/library/
│   │   ├── LibraryApplication.java        # entry point
│   │   ├── model/Book.java
│   │   ├── exception/InvalidBookException.java
│   │   ├── repository/
│   │   │   ├── BookRepository.java        # interface
│   │   │   └── impl/
│   │   │       ├── InMemoryBookRepository.java
│   │   │       ├── JdbcBookRepository.java
│   │   │       └── DatabaseConnection.java
│   │   ├── service/
│   │   │   ├── LibraryService.java        # interface
│   │   │   ├── impl/LibraryServiceImpl.java
│   │   │   ├── AddBookResult.java
│   │   │   ├── BorrowBookResult.java
│   │   │   ├── ReturnBookResult.java
│   │   │   └── DeleteBookResult.java
│   │   └── ui/
│   │       ├── ConsoleInput.java
│   │       └── MainMenu.java
│   └── resources/
│       └── application.properties.example
└── test/
    └── java/com/bhavesh/library/
        ├── model/BookTest.java
        └── service/impl/
            ├── FakeBookRepository.java
            └── LibraryServiceImplTest.java
```

## Database Setup

This project uses PostgreSQL. You'll need it installed and running
locally.

1. Create a database:
   ```sql
   CREATE DATABASE library_db;
   ```
2. Create the `books` table:
   ```sql
   CREATE TABLE books (
       id SERIAL PRIMARY KEY,
       title VARCHAR(255) NOT NULL,
       author VARCHAR(255) NOT NULL,
       isbn VARCHAR(20) NOT NULL UNIQUE,
       quantity INTEGER NOT NULL CHECK (quantity >= 0)
   );
   ```
3. Copy the config template and fill in your real credentials:
   ```bash
   cp src/main/resources/application.properties.example src/main/resources/application.properties
   ```
   Then edit `application.properties` with your actual `db.url`,
   `db.username`, and `db.password`.

   This file is gitignored on purpose — never commit real database
   credentials.

## How to Run

```bash
mvn compile
mvn exec:java -Dexec.mainClass="com.bhavesh.library.LibraryApplication"
```

Or run `LibraryApplication.main()` directly from your IDE.

## Running Tests

```bash
mvn test
```

## Sample Menu

```text
1. Display Books
2. Search Book by ISBN
3. Borrow Book
4. Return Book
5. Add Book
6. Delete Book
7. Search Books by Title
8. Exit
```

## Learning Outcomes

This project was built incrementally as a practice project, starting
from plain OOP and console I/O and layering in exception handling,
Streams, JUnit testing, and finally JDBC persistence — each step kept
intentionally simple and reviewed for correctness rather than
over-engineered.

## Possible Future Improvements

- Test coverage for `returnBook` and remaining `borrowBook` branches
- Integration tests against a real (or disposable/test) database for
  `JdbcBookRepository`
- Transactions, once an operation genuinely needs multi-step atomicity
- Due dates and fine calculation
- Borrowing history
- Multiple library members / user accounts

## Author

**Bhavesh Dewangan**

GitHub: https://github.com/bhaveshdew18
