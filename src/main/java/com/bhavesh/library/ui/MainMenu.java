package com.bhavesh.library.ui;

import java.util.Scanner;

public class MainMenu {
    private final Scanner scanner = new Scanner(System.in);

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
        System.out.println("5. Exit");
    }

    private int readChoice() {
        return scanner.nextInt();

    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                return true;

            case 2:
                return true;

            case 3:
                return true;

            case 4:
                return true;

            case 5:
                return false;

            default:
                return true;
        }
    }


}

