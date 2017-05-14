package Blatt04.library;

/**
 * A class to represent a blu ray to be stored in a library
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version likely final
 */
public class BluRay extends LibraryItem {
    /**
     * title of the blu ray
     */
    private String title;
    /**
     * director of the blu ray
     */
    private String director;

    /**
     * creating a new blu ray!
     * @param title title of the blu ray
     * @param director director of the blu ray
     */
    public BluRay(String title, String director) {
        this.title = title;
        this.director = director;
    }

    /**
     * @return title of blu ray
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return director of the blu ray
     */
    public String getDirector() {
        return director;
    }

    /**
     * The description holds all information available of the blu ray
     * Availability, Title, Director
     * @return the description
     */
    @Override
    public String getDescription() {
        String available = isBorrowed()? "not available" : "available";
        return title + " was directed by " + director + "\nThe item is currently " + available + ".";
    }
}
