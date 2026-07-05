import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementApp {

    public static void printMenu() {
        System.out.println("1. Display Books");
        System.out.println("2. Search Book");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Exit");
    }

    public static void performOperation(int choice, ArrayList<Book> books, Scanner scanner) {
        switch (choice) {
            case 1:
                displayBooks(books);
                break;
            
            case 2:
                searchBookByIsbn(books, scanner);
                break;
            
            case 3:
                borrowBook(books, scanner);
                break;
            
            case 4:
                returnBook(books, scanner);
                break;

            case 5:
                System.out.println("Exiting");
                break;

            default:
                System.out.println("Invalid option!");
                break;
        }
    }

    public static void displayBook(Book book) {
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("Publisher: " + book.getPublisher());
        System.out.println("Publication Year: " + book.getPublicationYear());
        System.out.println("ISBN: " + book.getIsbn());
        if (book.isAvailable()) {
            System.out.println("Status: Available");
        } else {
            System.out.println("Status: Borrowed");
        }
        System.out.println();
    }

    public static void displayBooks(ArrayList<Book> books) {
        for (Book book : books) {
            displayBook(book);
        }
    }

    public static void searchBookByIsbn(ArrayList<Book> books, Scanner scanner) {
        System.out.println("Enter ISBN: ");
        String searchISBN = scanner.next();
        Book book = findBookByIsbn(books, searchISBN);
        if(book != null) {
            displayBook(book);
        } else {
            System.out.println("Book not found.");
        }
    }

    public static Book findBookByIsbn(ArrayList<Book> books, String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public static void borrowBook(ArrayList<Book> books, Scanner scanner) {
        System.out.println("Enter ISBN: ");
        String searchISBN = scanner.next();
        Book book = findBookByIsbn(books, searchISBN);

        if (book != null) {
            book.borrowBook();
        } else {
            System.out.println("Book not found.");
        }

    }

    public static void returnBook(ArrayList<Book> books, Scanner scanner) {
        System.out.println("Enter ISBN: ");
        String searchISBN = scanner.next();
        Book book = findBookByIsbn(books, searchISBN);

        if (book != null) {
            book.returnBook();
        } else {
            System.out.println("Book not found.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;
        ArrayList<Book> books = new ArrayList<>();

        Book book1 = new Book(
                "Clean Code",
                "Robert C. Martin",
                "Prentice Hall",
                2008,
                "9780132350884");

        Book book2 = new Book(
                "Effective Java",
                "Joshua Bloch",
                "Addison-Wesley",
                2018,
                "9780134685991");

        Book book3 = new Book(
                "Head First Java",
                "Kathy Sierra & Bert Bates",
                "O'Reilly Media",
                2022,
                "9781491910771");

        books.add(book1);
        books.add(book2);
        books.add(book3);

         do {
            printMenu();
            System.out.println("Choose an option: ");
            choice = scanner.nextInt();
            performOperation(choice, books, scanner);
        } while (choice != 5);

        scanner.close();
    }
}
