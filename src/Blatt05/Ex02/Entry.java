package Blatt05.Ex02;

/**
 * An Entry holds an Object <code>o</code> and a reference <code>next</code> to
 * the next Entry such that a linked List of Entry elements is generated.
 * JavaDoc taken from: {@link Blatt05.Ex02.util.Entry}
 *
 * Overwritten to replace {@link #o} to be of type "T" instead of Object and to not have unused variables
 */
public class Entry<T> implements Cloneable{

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

    /**
     * Implements clone as described in {@link Object#clone()}
     * It is also not implementable without having to suppress unchecked
     * warnings in the editor, nor without avoiding to add the
     * "throws" though it will never throw, as we do in fact implement
     * the {@link Cloneable} interface.
     * @return a clone of this instance
     */
    @Override
    public Entry<T> clone() {
        try{
            @SuppressWarnings("unchecked")
            Entry<T> clone = (Entry<T>) super.clone();
            if (next != null) {
                clone.next = next.clone();
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError("Cloning not supported in clone method");
        }
    }

    /**
     * {@inheritDoc}
     * @param obj Object to test equality against
     * @return true if o is equal to this
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
        Entry other = (Entry) obj;
        if (next == null) {
            if (other.next != null) {
                return false;
            }
        }else if (!next.equals(other.next)){
            return false;
        }
        if (o == null){
            if (other.o != null) {
                return false;
            }
        } else if (!o.equals(other.o)) {
            return false;
        }
        return true;
    }

    /**
     * Formula taken from: http://docs.oracle.com/javase/1.5.0/docs/api/java/util/List.html#hashCode%28%29
     * {@inheritDoc}
     * Calculates the hashcode of this entry by adding up the hashcode of all following linked entries
     * @return hashcode of this entry
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 31*hashCode + (o==null ? 0 : o.hashCode());
        hashCode = 31*hashCode + (next==null ? 0 : next.hashCode());
        return hashCode;
    }

}
