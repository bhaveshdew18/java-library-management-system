package com.bhavesh.library.service.impl;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.service.AddBookResult;
import com.bhavesh.library.service.BorrowBookResult;
import com.bhavesh.library.service.DeleteBookResult;
import com.bhavesh.library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LibraryServiceImplTest {

    private FakeBookRepository fakeBookRepository;
    private LibraryService libraryService;

    @BeforeEach
    public void setUp()
    {
        fakeBookRepository = new FakeBookRepository();
        libraryService = new LibraryServiceImpl(fakeBookRepository);
    }

    @Test
    void addBook_returnsSuccess_whenBookIsValid()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "111", 5);

        AddBookResult addBookResult = libraryService.addBook(book);

        assertEquals(AddBookResult.SUCCESS, addBookResult);
    }

    @Test
    void addBook_returnsInvalidBook_whenBookIsNull()
    {
        AddBookResult addBookResult = libraryService.addBook(null);

        assertEquals(AddBookResult.INVALID_BOOK, addBookResult);
    }

    @Test
    void addBook_returnsSaveFailed_whenRepositorySaveFails()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "111", 5);
        fakeBookRepository.saveShouldFail = true;

        AddBookResult addBookResult = libraryService.addBook(book);

        assertEquals(AddBookResult.SAVE_FAILED, addBookResult);
    }

    @Test
    void addBook_returnsDuplicateIsbn_whenisbnAlreadyExists()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "111", 5);
        fakeBookRepository.save(book);

        Book book1 = new Book("Learn Java", "Oracle", "111", 6);

        AddBookResult addBookResult = libraryService.addBook(book1);

        assertEquals(AddBookResult.DUPLICATE_ISBN, addBookResult);
    }

    @Test
    void borrowBook_returnsSuccess_whenBookIsInStock()
    {

        Book book = new Book("Clean Code", "Robert C. Martin", "111", 5);
        fakeBookRepository.save(book);

        // Act
        BorrowBookResult result = libraryService.borrowBook(book.getIsbn());

        // Assert
        assertEquals(BorrowBookResult.SUCCESS, result);
        assertEquals(4, book.getQuantity());
    }

    @Test
    void borrowBook_returnsOutOfStock_whenQuantityIsZero()
    {
        Book book = new Book("Clean Code", "Robert C. Martin", "111", 1);
        fakeBookRepository.save(book);

        BorrowBookResult result = libraryService.borrowBook(book.getIsbn());
        BorrowBookResult result2 = libraryService.borrowBook(book.getIsbn());

        assertEquals(BorrowBookResult.OUT_OF_STOCK, result2);
        assertEquals(0, book.getQuantity());
    }

    @Test
    void deleteBook_returnsBookNotFound_whenIsbnDoesNotExist() {

        DeleteBookResult result  = libraryService.deleteBook("111");

        assertEquals(DeleteBookResult.BOOK_NOT_FOUND, result);
    }

    @Test
    void searchByTitle_returnsEmptyList_whenKeywordIsBlank()
    {
        List<Book> books = libraryService.searchByTitle(" ");

        assertEquals(books.isEmpty(), true);
    }
}