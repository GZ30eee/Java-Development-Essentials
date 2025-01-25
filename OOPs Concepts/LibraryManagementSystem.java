import java.util.ArrayList;
import java.util.Scanner;

// Base Book class
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Available: " + (isAvailable ? "Yes" : "No");
    }
}

// Specialized type of book
class ReferenceBook extends Book {
    public ReferenceBook(String title, String author, String isbn) {
        super(title, author, isbn);
    }

    @Override
    public String toString() {
        return super.toString() + " (Reference Book - Cannot be borrowed)";
    }
}

// Library System
class Library {
    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully!");
    }

    public void deleteBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        System.out.println("Book deleted successfully!");
    }

    public void searchBook(String keyword) {
        System.out.println("Search Results:");
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getAuthor().toLowerCase().contains(keyword.toLowerCase()) ||
                book.getIsbn().contains(keyword)) {
                System.out.println(book);
            }
        }
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LibraryManagementSystem {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. Search Book");
            System.out.println("4. Display All Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    library.addBook(new Book(title, author, isbn));
                }
                case 2 -> {
                    System.out.print("Enter ISBN to delete: ");
                    String isbn = scanner.nextLine();
                    library.deleteBook(isbn);
                }
                case 3 -> {
                    System.out.print("Enter keyword to search: ");
                    String keyword = scanner.nextLine();
                    library.searchBook(keyword);
                }
                case 4 -> library.displayBooks();
                case 5 -> {
                    System.out.println("Exiting Library Management System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
