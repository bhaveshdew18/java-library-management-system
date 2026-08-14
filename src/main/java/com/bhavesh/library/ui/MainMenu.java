package com.bhavesh.library.ui;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.service.LibraryService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);
    private final LibraryService libraryService;

    public MainMenu(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void start() {
        int choice;
        boolean running = true;
        while (running) {
            displayMenu();
            choice = readChoice();
            running = handleChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("1. Display Books");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Add Book");
        System.out.println("6. Exit");
    }

    private int readChoice() {
        return scanner.nextInt();

    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                List<Book> books = libraryService.getAllBooks();
                if (books.isEmpty()) {
                    System.out.println("No books available.");
                } else {
                    for (Book book : books) {
                        System.out.println(book);
                    }
                }
                return true;

            case 2:
                String isbn = scanner.next();
                Optional<Book> result = libraryService.searchBook(isbn);
                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Book not found.");
                }
                return true;

            case 3:
                isbn = scanner.next();
                if (libraryService.borrowBook(isbn)) {
                    System.out.println("Book borrowed successfully.");
                } else {
                    System.out.println("Unable to borrow book.");
                }
                return true;

            case 4:
                isbn = scanner.next();
                int copies = scanner.nextInt();
                if (libraryService.returnBook(isbn, copies)) {
                    System.out.println("Book returned successfully.");
                }  else {
                    System.out.println("Unable to return.");
                }
                return true;

            case 5:
                String title = scanner.next();
                String author = scanner.next();
                isbn = scanner.next();
                int quantity = scanner.nextInt();
                Book newBook = new Book(title, author, isbn, quantity);
                if(libraryService.addBook(newBook)) {
                    System.out.println("Book added successfully.");
                }  else {
                    System.out.println("Unable to add book.");
                }
                return true;

            case 6:
                return false;

            default:
                return true;
        }
    }


}

