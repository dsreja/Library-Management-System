import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Book class to represent a book in the library
class Book {
    private String title;
    private String author;
    private String isbn;
    private boolean isBorrowed;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isBorrowed = false;
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

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void borrowBook() {
        isBorrowed = true;
    }

    public void returnBook() {
        isBorrowed = false;
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + isbn + ", Borrowed: " + isBorrowed;
    }
}

// Library class to manage the collection of books
class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public Book searchBookByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    public void borrowBook(String isbn) {
        Book book = searchBookByIsbn(isbn);
        if (book != null && !book.isBorrowed()) {
            book.borrowBook();
            System.out.println("You have borrowed: " + book.getTitle());
        } else if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Book is already borrowed.");
        }
    }

    public void returnBook(String isbn) {
        Book book = searchBookByIsbn(isbn);
        if (book != null && book.isBorrowed()) {
            book.returnBook();
            System.out.println("You have returned: " + book.getTitle());
        } else if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println("Book was not borrowed.");
        }
    }

    public void listAllBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

// Main class with the Library Management System
public class LibraryManagementSystem {

    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by ISBN");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. List All Books");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter ISBN: ");
                    String isbn = scanner.nextLine();
                    Book newBook = new Book(title, author, isbn);
                    library.addBook(newBook);
                    System.out.println("Book added successfully.");
                    break;

                case 2:
                    System.out.print("Enter ISBN of the book to remove: ");
                    String removeIsbn = scanner.nextLine();
                    library.removeBook(removeIsbn);
                    System.out.println("Book removed successfully.");
                    break;

                case 3:
                    System.out.print("Enter title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBookByTitle = library.searchBookByTitle(searchTitle);
                    if (foundBookByTitle != null) {
                        System.out.println("Book found: " + foundBookByTitle);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 4:
                    System.out.print("Enter ISBN to search: ");
                    String searchIsbn = scanner.nextLine();
                    Book foundBookByIsbn = library.searchBookByIsbn(searchIsbn);
                    if (foundBookByIsbn != null) {
                        System.out.println("Book found: " + foundBookByIsbn);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;

                case 5:
                    System.out.print("Enter ISBN to borrow: ");
                    String borrowIsbn = scanner.nextLine();
                    library.borrowBook(borrowIsbn);
                    break;

                case 6:
                    System.out.print("Enter ISBN to return: ");
                    String returnIsbn = scanner.nextLine();
                    library.returnBook(returnIsbn);
                    break;

                case 7:
                    library.listAllBooks();
                    break;

                case 8:
                    exit = true;
                    System.out.println("Exiting system.");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }
}
