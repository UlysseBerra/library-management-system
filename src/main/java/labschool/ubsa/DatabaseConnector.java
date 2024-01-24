package labschool.ubsa;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

public class DatabaseConnector {
    private MongoClient mongoClient;
    private MongoDatabase database;

    public DatabaseConnector() {
        this.mongoClient = MongoClients.create("mongodb://localhost:27017");
        this.database = mongoClient.getDatabase("library");
    }

    public void initDatabase() {
        if (!collectionExists("books")) {
            database.createCollection("books");
        }
    }

    public void addBook(Book book) {
        MongoCollection<Document> collection = database.getCollection("books");

        Document document = new Document("title", book.getTitle())
                .append("authors", book.getAuthors())
                .append("genre", book.getGenre())
                .append("year", book.getYear())
                .append("ISBN", book.getISBN())
                .append("available", book.isAvailable())
                .append("borrower", book.getBorrower());

        collection.insertOne(document);
    }

    public ArrayList<Document> getAllBooks() {
        ArrayList<Document> booksList = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("books");
        FindIterable<Document> cursor = collection.find();

        try (final MongoCursor<Document> cursorIterator = cursor.iterator()) {
            while (cursorIterator.hasNext()) {
                booksList.add(cursorIterator.next());
            }
        }

        return booksList;
    }

    public ArrayList<Document> getBooks(String cat, String crit) {
        ArrayList<Document> booksList = new ArrayList<>();

        MongoCollection<Document> collection = database.getCollection("books");
        Document query = new Document(cat, crit);
        FindIterable<Document> cursor = collection.find(query);

        try (final MongoCursor<Document> cursorIterator = cursor.iterator()) {
            while (cursorIterator.hasNext()) {
                booksList.add(cursorIterator.next());
            }
        }

        return booksList;
    }

    public void removeBookByISBN(String ISBN) {
        MongoCollection<Document> collection = database.getCollection("books");

        Document searchQuery = new Document("ISBN", ISBN);

        collection.deleteMany(searchQuery);
    }


    private boolean collectionExists(String collectionName) {
        for (String collection : database.listCollectionNames()) {
            if (collectionName.equals(collection)) {
                return true;
            }
        }
        return false;
    }
}