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
        if(book == null){
            return false;
        }
        if(book.getIsbn().isBlank()){
            return false;
        }
        if(book.getTitle().isBlank()){
            return false;
        }
        if(book.getAuthor().isBlank()){
            return false;
        }
        if(book.getQuantity() <= 0) {
            return false;
        }

        return repository.save(book);
    }

    @Override
    public Optional<Book> searchBook(String isbn) {
        if(isbn == null){
            return Optional.empty();
        }
        if(isbn.isBlank()){
            return Optional.empty();
        }
        return repository.findByIsbn(isbn);
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public boolean borrowBook(String isbn) {
        if(isbn == null){
            return false;
        }
        if(isbn.isBlank()){
            return false;
        }

        Optional<Book> result = repository.findByIsbn(isbn);
        if(result.isPresent()){
            Book book = result.get();
            if(!book.borrowBook()) {
                return false;
            }
            return repository.update(book);
        }

        return false;
    }

    @Override
    public boolean returnBook(String isbn, int copies) {
        if(isbn == null){
            return false;
        }
        if(isbn.isBlank()){
            return false;
        }
        if(copies <= 0){
            return false;
        }
        Optional<Book> result = repository.findByIsbn(isbn);
        if(result.isPresent()){
            Book book = result.get();
            if(!book.returnBook(copies)) {
                return false;
            }
            return repository.update(book);
        }
        return false;
    }
}
