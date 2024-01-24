package labschool.ubsa;

import org.apache.log4j.BasicConfigurator;
import org.bson.Document;

import java.util.ArrayList;

public class Library {
    private DatabaseConnector conn;
    public Library() {
        BasicConfigurator.configure();
        conn = new DatabaseConnector();
    }

    public ArrayList<Book> getAllBooks() {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getAllBooks()) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByISBN(String ISBN) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("ISBN", ISBN)) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("author", author)) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("title", title)) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByGenre(String genre) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("genre", genre)) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByAvailable(boolean available) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("available", String.valueOf(available))) {
            list.add(new Book(i));
        }

        return list;
    }

    public ArrayList<Book> getBooksByBorrower(String borrower) {
        ArrayList<Book> list = new ArrayList<>();

        for(Document i : conn.getBooks("borrower", borrower)) {
            list.add(new Book(i));
        }

        return list;
    }

    public void addBook(Book b) {
        conn.addBook(b);
    }

    public void removeBookByISBN(String ISBN) {
        conn.removeBookByISBN(ISBN);
    }

    public void borrowBook(Book b, String borrower) {
        removeBookByISBN(b.getISBN());

        b.setBorrower(borrower);
        b.setAvailable(false);

        addBook(b);
    }

    public void returnBook(Book b) {
        removeBookByISBN(b.getISBN());

        b.setBorrower("");
        b.setAvailable(true);

        addBook(b);
    }
}