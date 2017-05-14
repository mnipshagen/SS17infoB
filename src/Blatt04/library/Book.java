package Blatt04.library;

/**
 * A class to represent a book to be stored in a library
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version likely final
 */
public class Book extends LibraryItem {
    /**
     * title of the book
     */
    private String title;
    /**
     * author of the book
     */
    private String author;

    /**
     * A new book
     * @param title title of the book
     * @param author author of the book
     */
    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    /**
     * @return the title of the book
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * The description holds all information available of the book
     * Availability, Title, Author
     * @return the description
     */
    @Override
    public String getDescription() {
        String available = isBorrowed()? "not available" : "available";
        return title + " was written by " + author + "\nThe item is currently " + available + ".";
    }
}
