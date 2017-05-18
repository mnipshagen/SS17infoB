package Blatt05.Ex02;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * JavaDoc taken from: {@link Blatt05.Ex02.util.Entry}
 *
 * Overwritten to replace {@link #o} to be of type "T" instead of Object
 */
public class Entry<T> {

    T o;
    Entry<T> next;

    Entry() {
        this(null, null);
    }

    Entry(T o) {
        this(o, null);
    }

    Entry(T o, Entry<T> e) {
        this.o = o;
        this.next = e;
    }
    
}
