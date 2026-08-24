package com.bhavesh.library;

import com.bhavesh.library.repository.BookRepository;
import com.bhavesh.library.repository.impl.JdbcBookRepository;
import com.bhavesh.library.service.LibraryService;
import com.bhavesh.library.service.impl.LibraryServiceImpl;
import com.bhavesh.library.ui.ConsoleInput;
import com.bhavesh.library.ui.MainMenu;

public class LibraryApplication {
    public void start() {
        BookRepository bookRepository = new JdbcBookRepository();

        LibraryService libraryService = new LibraryServiceImpl(bookRepository);

        ConsoleInput consoleInput = new ConsoleInput();

        MainMenu mainMenu = new MainMenu(libraryService,consoleInput);
        mainMenu.start();
    }

    public static void main(String[] args) {
        LibraryApplication app = new LibraryApplication();
        app.start();
    }
}
