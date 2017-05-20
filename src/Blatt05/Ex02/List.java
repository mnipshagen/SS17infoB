package Blatt05.Ex02;

import java.util.NoSuchElementException;

/**
 * A type safety linked list. One may go through this list by {@link #advance()}
 * until the last position ({@link #endpos()}) is reached. One also may
 * {@link #delete()} and {@link #add(Object)} elements. After advancing it is
 * possible to go back to the beginning by {@link #reset()}.
 *
 * JavaDoc taken in great parts from {@link Blatt05.Ex02.util.List}
 *
 * {@link Blatt05.Ex02.util.List} was rewritten instead of extended, since
 * we would have to Override {@link Blatt05.Ex02.util.List#add(Object)} to
 * enforce type usage. Since method signatures include the parameters though,
 * we could not have overwritten that method with <code>T</code> instead of
 * Object as parameter.
 *
 * @author Moritz Nipshagen (mnipshagen@uos.de)
 * @author Tobias Ludwig (toludwig@uos.de)
 */
public class List<T> implements Cloneable{

    /**
     * Reference on the first entry of this List
     */
    private Entry<T> begin;
    /**
     * References before the current entry of this List
     */
    private Entry<T> pos;
// debugging reasons
//    public int count;

    /**
     * Create a new empty List.
     */
    public List() {
        pos = begin = new Entry<>();
//        count = 0;
    }

    /**
     * Determines if this List is empty or not.
     * @return <code>true</code>, if there are no elements in this List
     */
    public boolean empty() {
        return begin.next == null;
    }

    /**
     * Determines if it is possible to {@link #advance()} in this List. Returns
     * <code>true</code> if the last position of this List has been reached. An
     * {@link #empty()} List will always deliver <code>true</code>
     *
     * @return <code>true</code> if the last Entry in this List already has been
     *         reached.
     */
    public boolean endpos() { // true, wenn am Ende
        return pos.next == null;
    }

    /**
     * Returns to the beginning of this List.
     */
    public void reset() {
        pos = begin;
    }

    /**
     * Advances one step in this List.
     * @throws RuntimeException
     *            if the last entry of this List has been reached already
     */
    public void advance() {
        if (endpos()) {
            throw new NoSuchElementException("Already at the end of this List");
        }
        pos = pos.next;
    }

    /**
     * Returns the current element of this List.
     * @return the current element
     * @throws RuntimeException
     *            if the last entry of this List has been reached already.
     */
    public T elem() {
        if (endpos()) {
            throw new NoSuchElementException("Already at the end of this List");
        }
        return pos.next.o;
    }

    /**
     * Inserts <code>o</code> in this List. It will be placed before the current
     * element. After insertion the inserted element will become the current
     * element.
     * @param x the element to be inserted
     */
    public void add(T x) {
//        count ++;
        pos.next = new Entry<>(x, pos.next);
    }

    /**
     * Deletes the current element of this List. The element after the current
     * element will become the new current element.
     * @throws RuntimeException if the last entry of this List has been reached already
     */
    public void delete() {
        if (endpos()) {
            throw new RuntimeException("Already at the end of this List");
        }
//        count --;
        pos.next = pos.next.next;
    }

    /**
     * Implements clone as described in {@link Object#clone()}
     * It is also not implementable without having to suppress unchecked
     * warnings in the editor, nor without avoiding to add the
     * "throws" though it will never throw, as we do in fact implement
     * the {@link Cloneable} interface.
     * @return a clone of this instance
     */
    @Override
    public List<T> clone() {
        try {
            @SuppressWarnings("unchecked")
            List<T> clone = (List<T>) super.clone();
            clone.begin = begin.clone();
            clone.pos = clone.begin;
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError("Cloning not supported in clone method");
        }
    }

    /**
     * Formula taken from: http://docs.oracle.com/javase/1.5.0/docs/api/java/util/List.html#hashCode%28%29
     * {@inheritDoc}
     * Calculates the hashcode of the list by adding up the hashcode of all elements in the list
     * The iterating works through {@link Entry#hashCode()} as we there iterate through all linked entries
     * @return hashcode of the list
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31 * hashCode + (begin==null ? 0 : begin.hashCode());
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * Implemented to satisfy all non-absolute requirements of {@link #clone()}
     * Tests whether <code>obj</code> and this list are equals
     * Both are equals iff:
     *      all their entries are equal
 *      or  they are indeed equal by reference <code>==</code>
     * @param obj Object to test equality against
     * @return true if obj is equal to this list
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        List l = (List) obj;
        return begin.equals(l.begin);
    }
}
