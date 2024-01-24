package labschool.ubsa;

import org.bson.Document;

import java.util.ArrayList;

public class Book {
    private static int count = 0;
    private int id;
    private String title;
    private ArrayList<String> authors;
    private String genre;
    private int year;
    private String ISBN;
    private boolean available;
    private String borrower;

    public Book(String title, ArrayList<String> authors, String genre, int year, String ISBN) {
        this.id = count;
        count++;
        this.title = title;
        this.authors = authors;
        this.genre = genre;
        this.year = year;
        this.ISBN = ISBN;
        this.available = true;
        this.borrower = "";
    }
    public Book(Document doc) {
        this.title = doc.getString("title");
        this.authors = (ArrayList<String>) doc.getList("authors", String.class);
        this.genre = doc.getString("genre");
        this.year = doc.getInteger("year");
        this.ISBN = doc.getString("ISBN");
        this.available = doc.getBoolean("available");
        this.borrower = doc.getString("borrower");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBorrower() {
        return borrower;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }
}