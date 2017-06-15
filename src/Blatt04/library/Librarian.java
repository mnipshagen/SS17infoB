package Blatt04.library;

import Blatt04.library.util.List;

/**
 * A class to test the library system
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version likely final
 */
public class Librarian
{

    public static void main(String[] args)
    {
        // some blurays
        BluRay br1 = new BluRay("The Lord of the Rings", "Peter Jackson");
        BluRay br2 = new BluRay("Beauty and the Beast", "Bill Condon");
        BluRay br3 = new BluRay("Harry Potter and the Prisoner of Azkaban", "Alfonso Cuarón");

        // some books
        Book b1 = new Book("Harry Potter and the deathly hallows", "Joanne K. Rowling");
        Book b2 = new Book("The Lord of the Rings", "J.R.R. Tolkien");
        Book b3 = new Book("Game of Thrones", "George R.R. Martin");

        // checking item methods
        assert br1.getTitle().equals("The Lord of the Rings") : "BluRay title wrong";
        assert br2.getDirector().equals("Bill Condon") : "BluRay director wrong";
        assert b1.getAuthor().equals("Joanne K. Rowling") : "Book author wrong";
        assert b3.getTitle().equals("Game of Thrones") : "Book title wrong";
        assert !br3.isBorrowed() : "Availability false";

        // adding all the items to a library
        Library l = new Library();
        l.addItem(br1);
        l.addItem(br2);
        l.addItem(br3);
        l.addItem(b1);
        l.addItem(b2);
        l.addItem(b3);

        // search for all the items
        // then check whether we got them all
        List res = l.search("");
        res.reset();
        assert res.elem() == br1 : "Second element is wrong";
        res.advance();
        assert res.elem() == br2 : "Third element is wrong";
        res.advance();
        assert res.elem() == br3 : "Fourth element is wrong";
        // checking borrowed function and if res really only holds referenced
        // Part 1
        ((LibraryItem) res.elem()).setBorrowed(true);
        res.advance();
        assert res.elem() == b1 : "Fifth element is wrong";
        res.advance();
        assert res.elem() == b2 : "Sixth element is wrong";
        res.advance();
        assert res.elem() == b3 : "Seventh element is wrong";
        res.advance();
        // is there something left?
        assert res.endpos() : "res item count too high";
        // Part 2
        assert br3.isBorrowed() : "BluRay 3 should be borrowed";

        // delete an item!
        l.deleteItem(b3);
        // Is it still there?
        res = l.search("R.R.");
        res.reset();
        assert res.elem() == b2 : "Second Search went wrong #1";
        res.advance();
        assert res.endpos() : "res item count too high";
        // Aaaaand Done.
        System.out.println("Done.");
    }
}
