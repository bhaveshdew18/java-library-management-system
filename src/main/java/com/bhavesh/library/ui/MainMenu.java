package com.bhavesh.library.ui;

import com.bhavesh.library.exception.InvalidBookException;
import com.bhavesh.library.model.Book;
import com.bhavesh.library.service.AddBookResult;
import com.bhavesh.library.service.BorrowBookResult;
import com.bhavesh.library.service.LibraryService;
import com.bhavesh.library.service.ReturnBookResult;

import java.util.List;
import java.util.Optional;

public class MainMenu {
    private final ConsoleInput input;
    private final LibraryService libraryService;

    public MainMenu(LibraryService libraryService, ConsoleInput input) {
        this.input = input;
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
      return input.readInt("Choose an option: ");
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
                String isbn = input.readLine("Enter ISBN: ");
                Optional<Book> result = libraryService.searchBook(isbn);
                if (result.isPresent()) {
                    System.out.println(result.get());
                } else {
                    System.out.println("Book not found.");
                }
                return true;

            case 3:
                isbn = input.readLine("Enter ISBN: ");
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
                isbn = input.readLine("Enter ISBN: ");
                int copies = input.readInt("Enter copies: ");

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
                String title = input.readLine("Enter title.");
                String author = input.readLine("Enter author.");
                isbn = input.readLine("Enter ISBN.");
                int quantity = input.readInt("Enter quantity.");

                try {
                    Book newBook = new Book(title, author, isbn, quantity);
                    AddBookResult addResult = libraryService.addBook(newBook);

                    switch (addResult) {
                        case SUCCESS -> System.out.println("Book added successfully.");
                        case DUPLICATE_ISBN -> System.out.println("A book with this ISBN already exists.");
                        case SAVE_FAILED -> System.out.println("Unable to save book.");
                        case INVALID_BOOK -> System.out.println("Invalid book details.");
                    }
                } catch (InvalidBookException e) {
                    System.out.println("Could not add book: " + e.getMessage());
                }

                return true;

            case 6:
                return false;

            default:
                return true;
        }
    }


}

