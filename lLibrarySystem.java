import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Book {
    private String title;
    private String author;
    private int quantity;

    public Book(String title, String author, int quantity) {
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Library {
    private Map<String, List<Book>> booksByAuthor;

    public Library() {
        this.booksByAuthor = new HashMap<>();
    }

    public void addBook(String author, Book book) {
        if (!booksByAuthor.containsKey(author)) {
            booksByAuthor.put(author, new ArrayList<>());
        }
        booksByAuthor.get(author).add(book);
    }

    public void displayBooksByAuthor(String author) {
        if (booksByAuthor.containsKey(author)) {
            System.out.println("Books by " + author + ":");
            for (Book book : booksByAuthor.get(author)) {
                System.out.println("Title: " + book.getTitle() + ", Quantity: " + book.getQuantity());
            }
        } else {
            System.out.println("No books found for " + author);
        }
    }

    public void borrowBook(String author, String title) {
        if (booksByAuthor.containsKey(author)) {
            for (Book book : booksByAuthor.get(author)) {
                if (book.getTitle().equals(title)) {
                    if (book.getQuantity() > 0) {
                        book.setQuantity(book.getQuantity() - 1);
                        System.out.println("You have borrowed the book: " + title);
                        return;
                    } else {
                        System.out.println("No copies of " + title + " available to borrow.");
                        return;
                    }
                }
            }
            System.out.println("Book not found in the library.");
        } else {
            System.out.println("No books found for " + author);
        }
    }
}

class LibrarySystem {
    public static void main(String[] args) {
        Library library = new Library();
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 3);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 2);
        Book book3 = new Book("1984", "George Orwell", 4);

        library.addBook("F. Scott Fitzgerald", book1);
        library.addBook("Harper Lee", book2);
        library.addBook("George Orwell", book3);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Display Books by Author\n2. Borrow Book\n3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter author name: ");
                    String author = scanner.nextLine();
                    library.displayBooksByAuthor(author);
                    break;
                case 2:
                    System.out.print("Enter author name: ");
                    String borrowAuthor = scanner.nextLine();
                    System.out.print("Enter book title: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowAuthor, borrowTitle);
                    break;
                case 3:
                    System.out.println("Exiting the system. Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
