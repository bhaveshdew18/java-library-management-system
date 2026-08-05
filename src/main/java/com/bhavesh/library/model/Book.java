package com.bhavesh.library.model;

public class Book {
    private  String title;
    private String author;
    private String isbn;
    private int quantity;

    public Book(String title, String author, String isbn, int quantity) {
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
