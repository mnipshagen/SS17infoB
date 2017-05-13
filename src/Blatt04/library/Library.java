package Blatt04.library;

import Blatt04.library.util.List;

/**
 * A class to represent a simple library
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version likely final
 */
public class Library {
    /**
     * stores all the items in the library
     */
    private List inventory;

    /**
     * A new Library holds an empty inventory
     */
    public Library() {
        this.inventory = new List();
    }

    /**
     * Adds an item to the library inventory
     * @param item to be added
     */
    public void addItem(LibraryItem item) {
        inventory.add(item);
    }

    /**
     * Iterate through the inventory and try to find item by comparing descriptions
     * Then delete it
     * @param item to be removed from library
     */
    public void deleteItem(LibraryItem item) {
        inventory.reset();
        while(! inventory.endpos() &&
                !((LibraryItem)inventory.elem()).getDescription().equals(item.getDescription())) {
            inventory.advance();
        }
        inventory.delete();
    }

    /**
     * Search the inventory by looking at the item's descriptions
     * @param text the string to search for in the descriptions
     * @return List of all applicable items
     */
    public List search(String text) {
        List result = new List();
        inventory.reset();
        while(!inventory.endpos()) {
            if (((LibraryItem)inventory.elem()).getDescription().contains(text)) {
                result.add(inventory.elem());
            }
            inventory.advance();
        }
        return result;
    }
}
