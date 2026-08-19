package com.bhavesh.library.repository.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository
{
    private final List<Book> books;

    public InMemoryBookRepository(List<Book> books) {
        this.books = new ArrayList<>(books);
    }

    @Override
    public boolean save(Book book) {
        if (findByIsbn(book.getIsbn()).isPresent()) {
            return false;
        }
        else {
            books.add(book);
            return true;
        }
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(books);
    }

    @Override
    public List<Book> findByTitle(String keyword) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    @Override
    public boolean update(Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(book.getIsbn())) {
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(String isbn) {
        for (int i  = 0; i < books.size(); i++) {
            if (books.get(i).getIsbn().equals(isbn)) {
                books.remove(i);
                return true;
            }
        }
        return false;
    }
}
