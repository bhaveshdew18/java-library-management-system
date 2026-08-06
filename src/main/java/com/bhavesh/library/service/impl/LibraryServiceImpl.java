package com.bhavesh.library.service.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;
import com.bhavesh.library.service.LibraryService;

import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    private final BookRepository repository;
    public LibraryServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public boolean addBook(Book book) {

    }

    @Override
    public Optional<Book> searchBook(String isbn) {
        return Optional.empty();
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }

    @Override
    public boolean borrowBook(String isbn) {
        return false;
    }

    @Override
    public boolean returnBook(String isbn, int copies) {
        return false;
    }
}
