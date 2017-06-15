package Blatt04.library;

/**
 * A class to represent an item to be stored in a library
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version likely final
 */
public abstract class LibraryItem
{
    /**
     * whether the item is currently available
     */
    private boolean isBorrowed;

    /**
     * a new item is not borrowed
     */
    public LibraryItem()
    {
        this.isBorrowed = false;
    }

    /**
     * retrieve status
     *
     * @return true if item is unavailable
     */
    public boolean isBorrowed()
    {
        return isBorrowed;
    }

    /**
     * change status of availability
     *
     * @param isBorrowed new status
     */
    public void setBorrowed(boolean isBorrowed)
    {
        this.isBorrowed = isBorrowed;
    }

    /**
     * Return a description of the item holding all information
     *
     * @return the description
     */
    public abstract String getDescription();

}
