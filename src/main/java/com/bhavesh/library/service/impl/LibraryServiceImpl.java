package com.bhavesh.library.service.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;
import com.bhavesh.library.service.*;

import java.util.List;
import java.util.Optional;

public class LibraryServiceImpl implements LibraryService {
    private final BookRepository repository;
    public LibraryServiceImpl(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public AddBookResult addBook(Book book) {
        if (book == null) {
            return AddBookResult.INVALID_BOOK;
        }

        if (repository.findByIsbn(book.getIsbn()).isPresent()) {
            return AddBookResult.DUPLICATE_ISBN;
        }

        return repository.save(book) ? AddBookResult.SUCCESS : AddBookResult.SAVE_FAILED;
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
    public List<Book> searchByTitle(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        return repository.findByTitle(keyword);
    }

    @Override
    public BorrowBookResult borrowBook(String isbn) {
        if(isbn == null){
            return BorrowBookResult.INVALID_ISBN;
        }
        if(isbn.isBlank()){
            return BorrowBookResult.INVALID_ISBN;
        }

        Optional<Book> result = repository.findByIsbn(isbn);
        if(result.isPresent()){
            Book book = result.get();
            if(!book.borrowBook()) {
                return BorrowBookResult.OUT_OF_STOCK;
            }
            if (repository.update(book)) {
                return BorrowBookResult.SUCCESS;
            }

            return BorrowBookResult.UPDATE_FAILED;
        }

        return BorrowBookResult.BOOK_NOT_FOUND;
    }

    @Override
    public ReturnBookResult returnBook(String isbn, int copies) {
        if (isbn == null) {
            return ReturnBookResult.INVALID_ISBN;
        }

        if (isbn.isBlank()) {
            return ReturnBookResult.INVALID_ISBN;
        }

        if (copies <= 0) {
            return ReturnBookResult.INVALID_COPIES;
        }

        Optional<Book> result = repository.findByIsbn(isbn);

        if (result.isPresent()) {
            Book book = result.get();

            book.returnBook(copies);

            if (repository.update(book)) {
                return ReturnBookResult.SUCCESS;
            }

            return ReturnBookResult.UPDATE_FAILED;
        }

        return ReturnBookResult.BOOK_NOT_FOUND;
    }

    @Override
    public DeleteBookResult deleteBook(String isbn) {
        if (isbn == null || isbn.isBlank()) {
            return DeleteBookResult.INVALID_ISBN;
        }

        if (repository.findByIsbn(isbn).isEmpty()) {
            return DeleteBookResult.BOOK_NOT_FOUND;
        }

        return repository.delete(isbn) ? DeleteBookResult.SUCCESS : DeleteBookResult.DELETE_FAILED;
    }
}
