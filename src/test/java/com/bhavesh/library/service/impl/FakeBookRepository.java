package com.bhavesh.library.service.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class FakeBookRepository implements BookRepository {

    private final Map<String, Book> booksByIsbn = new HashMap<>();
    boolean saveShouldFail = false;
    boolean updateShouldFail = false;
    boolean deleteShouldFail = false;

    @Override
    public boolean save(Book book) {
        if (saveShouldFail) {
            return false;
        }
        booksByIsbn.put(book.getIsbn(), book);
        return true;
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        return Optional.ofNullable(booksByIsbn.get(isbn));
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(booksByIsbn.values());
    }

    @Override
    public List<Book> findByTitle(String keyword) {
        return booksByIsbn.values().stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public boolean update(Book book) {
        if (updateShouldFail) {
            return false;
        }
        booksByIsbn.put(book.getIsbn(), book);
        return true;
    }

    @Override
    public boolean delete(String isbn) {
        if (deleteShouldFail) {
            return false;
        }
        return booksByIsbn.remove(isbn) != null;
    }
}
