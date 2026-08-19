package com.bhavesh.library.model;

import com.bhavesh.library.exception.InvalidBookException;

public class Book {
    private  String title;
    private String author;
    private String isbn;
    private int quantity;

    public Book(String title, String author, String isbn, int quantity) {

        if (title == null || title.isBlank()) {
            throw new InvalidBookException("Title cannot be blank.");
        }
        if (author == null || author.isBlank()) {
            throw new InvalidBookException("Author cannot be blank.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new InvalidBookException("ISBN cannot be blank.");
        }
        if (quantity <= 0) {
            throw new InvalidBookException("Quantity must be greater than zero.");
        }

        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", quantity=" + quantity +
                '}';
    }

    public boolean borrowBook() {
        if(quantity > 0) {
            quantity -= 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean returnBook(int copies) {
        if(copies > 0) {
            quantity += copies;
            return true;
        }
        return false;
    }
}
