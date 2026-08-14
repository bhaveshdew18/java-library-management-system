package com.bhavesh.library;

import com.bhavesh.library.model.Book;
import com.bhavesh.library.repository.BookRepository;
import com.bhavesh.library.repository.impl.InMemoryBookRepository;
import com.bhavesh.library.service.LibraryService;
import com.bhavesh.library.service.impl.LibraryServiceImpl;
import com.bhavesh.library.ui.MainMenu;

import java.util.ArrayList;
import java.util.List;

public class LibraryApplication {
    public void start() {
        List<Book> books = new ArrayList<>();
        BookRepository bookRepository = new InMemoryBookRepository(books);
        LibraryService libraryService = new LibraryServiceImpl(bookRepository);
        MainMenu mainMenu = new MainMenu(libraryService);
        mainMenu.start();
    }

    public static void main(String[] args) {
        LibraryApplication app = new LibraryApplication();
        app.start();
    }
}
