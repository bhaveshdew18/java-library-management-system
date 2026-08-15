package com.bhavesh.library.ui;

import java.util.Scanner;

public class ConsoleInput {
    private final Scanner scanner = new Scanner(System.in);

    public String readLine(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public int readInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } else {
                scanner.nextLine();
                System.out.println("Invalid input.");
            }
        }
    }
}
