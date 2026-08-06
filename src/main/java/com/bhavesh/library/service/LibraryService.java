package com.bhavesh.library.service;

import com.bhavesh.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    void addBook(Book book);

    Optional<Book> searchBook(String isbn);

    List<Book> getAllBooks();

    boolean borrowBook(String isbn);

    boolean returnBook(String isbn, int copies);

}
