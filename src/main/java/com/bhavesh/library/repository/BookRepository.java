package com.bhavesh.library.repository;

import com.bhavesh.library.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    boolean save(Book book);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findAll();

    boolean update(Book book);

    boolean delete(String isbn);
}
