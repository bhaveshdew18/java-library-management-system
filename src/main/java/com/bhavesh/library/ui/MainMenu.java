package com.bhavesh.library.ui;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.service.AddBookResult;
import com.bhavesh.library.service.BorrowBookResult;
import com.bhavesh.library.service.LibraryService;
import com.bhavesh.library.service.ReturnBookResult;

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
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
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
                BorrowBookResult borrowresult = libraryService.borrowBook(isbn);
                switch (borrowresult) {
                    case SUCCESS:
                        System.out.println("Book borrowed successfully.");
                        break;

                    case BOOK_NOT_FOUND:
                        System.out.println("Book not found.");
                        break;

                    case OUT_OF_STOCK:
                        System.out.println("Book is out of stock.");
                        break;

                    case INVALID_ISBN:
                        System.out.println("Invalid ISBN.");
                        break;

                    case UPDATE_FAILED:
                        System.out.println("Unable to update book.");
                        break;
                }
                return true;

            case 4:
                isbn = scanner.next();
                int copies = scanner.nextInt();

                ReturnBookResult returnResult = libraryService.returnBook(isbn, copies);

                switch (returnResult) {
                    case SUCCESS:
                        System.out.println("Book returned successfully.");
                        break;

                    case BOOK_NOT_FOUND:
                        System.out.println("Book not found.");
                        break;

                    case INVALID_ISBN:
                        System.out.println("Invalid ISBN.");
                        break;

                    case INVALID_COPIES:
                        System.out.println("Invalid number of copies.");
                        break;

                    case UPDATE_FAILED:
                        System.out.println("Unable to update book.");
                        break;
                }

                return true;

            case 5:
                String title = scanner.nextLine();
                String author = scanner.nextLine();
                isbn = scanner.nextLine();

                int quantity;

                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                } else {
                    scanner.nextLine();
                    System.out.println("Invalid input.");
                    return true;
                }

                Book newBook = new Book(title, author, isbn, quantity);

                AddBookResult addResult = libraryService.addBook(newBook);

                switch (addResult) {
                    case SUCCESS:
                        System.out.println("Book added successfully.");
                        break;

                    case INVALID_BOOK:
                        System.out.println("Invalid book details.");
                        break;

                    case DUPLICATE_ISBN:
                        System.out.println("A book with this ISBN already exists.");
                        break;

                    case SAVE_FAILED:
                        System.out.println("Unable to save book.");
                        break;
                }

                return true;
            case 6:
                return false;

            default:
                return true;
        }
    }


}

