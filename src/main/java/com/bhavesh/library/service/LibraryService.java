package com.bhavesh.library.service;

import com.bhavesh.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface LibraryService {
    AddBookResult addBook(Book book);

    Optional<Book> searchBook(String isbn);

    List<Book> getAllBooks();

    BorrowBookResult borrowBook(String isbn);

    ReturnBookResult returnBook(String isbn, int copies);
}
