public class Book {
    private String title;
    private String author;
    private String publisher;
    private int publicationYear;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String publisher, int publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void borrowBook() {
        if(available) {
            available = false;
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook() {
        if(!available) {
            available = true;
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book is already available.");
        }
    }


}