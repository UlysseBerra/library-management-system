package labschool.ubsa;

import org.apache.log4j.BasicConfigurator;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Library lsp = new Library();

        lsp.removeBookByISBN("isbn");

        ArrayList<String> authors = new ArrayList<String>();
        authors.add("author1");
        authors.add("author2");

        lsp.addBook(new Book("title", authors, "genre", 9999, "isbn"));

        Book b = lsp.getBooksByISBN("isbn").get(0);

        lsp.borrowBook(b, "me");

        System.out.println(lsp.getAllBooks());

        lsp.returnBook(b);

        System.out.println(lsp.getAllBooks());
    }
}