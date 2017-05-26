package Blatt05.Ex03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Authors: Moritz Nipshagen, Tobias Ludwig
 *
 * A typesafe heap working with elements implementing
 * Comparable or, alternatively ordered by a Comparator.
 */
public class Heap<T extends Comparable<T>> {

    private T[] array;
    private Comparator<T> c;

    /**
     * Constructor for setting the Comparator for the keys.
     * @param c the Comparator
     */
    public Heap(Comparator<T> c) {
        this.c = c;
    }

    /**
     * Default (empty) constructor.
     * Does not define a Comparator, keys are ordered by compareTo instead.
     */
    public Heap() { }

    /**
     * Wrapper for comparison of keys.
     * In case Comparator c is set, use that,
     * else, just compareTo on the keys,
     * which are Comparables.
     * @param key1
     * @param key2
     * @return value < 0 if key1 < key2, value==0 if key1==key2, value > 0 otherwise
     */
    private double compareKeys(T key1, T key2){
        if(c == null){
            return key1.compareTo(key2);
        } else{
            return c.compare(key1, key2);
        }
    }

    /**
     * Inserts an element into a heap.
     * The key is appended as the last node and then sifted
     * upwards until the heap criterion is fulfilled.
     * Due to sifting, this lies in O(log n).
     * @param key the key to be inserted
     */
    public void insert(T key) {
        if(this.empty()){ // for the very first insertion
            array = (T[]) new Comparable[]{key}; // just put the root
            return;                              // and finish
        }

        // copyOfRange uses array.length+1 as final index (which appends null)
        array = Arrays.copyOfRange(array, 0, array.length+1);
        array[array.length-1] = key; // set the key as last element

        // sifting upwards
        int curi = array.length-1; // current index
        int parent = -1;         // parent index, initially dummy
        while (curi != 0) {      // as long as we have not reached the root
            parent = (curi - 1) / 2; // calculate parent index
            if (compareKeys(array[parent], key) > 0) { // if parent larger
                array[curi] = array[parent]; // parent and child have
                array[parent] = key;         // to swap places
                curi = parent;
            } else              // otherwise the key can stay in place
                break;
        }
    }

    /**
     * Delete the minimum, the root node.
     * Requires reorganizing of the array in O(log n).
     * @throws IndexOutOfBoundsException if Heap is empty
     */
    public void del_min() throws Exception {
        // if heap empty, throw exception
        if(this.empty())
            throw new IndexOutOfBoundsException("Heap empty");

        // if only one element left, remove it and stop immediately
        if(array.length == 1){
            array = null;
            return;
        }

        // remove the last element and set it as the new root
        T last = array[array.length-1];
        array = Arrays.copyOfRange(array, 0, array.length-1);
        array[0] = last;

        // rebuild the array by sifting the new root down
        int curi = 0, childi = 1;
        while(childi < array.length){
            // find the smaller child
            T c1 = array[childi];
            T c2, cc; // cc will be the smaller of both children, the child to check
            if(childi+1 < array.length) { // if a second (right) child exists,
                c2 = array[childi+1];     // check for that, too
                if(compareKeys(c1, c2) < 0) // the smaller one is assigned to cc
                    cc = c1;
                else{
                    cc = c2;
                    childi++; // take the index of the right child
                }
            } else
                cc = c1;

            if(compareKeys(last, cc) > 0){
                array[curi] = array[childi];
                array[childi] = last;
                curi = childi;
                childi = 2*curi +1;
            } else
                break;
        }
    }


    /**
     * Returns the minimum, the root node.
     */
    public T get_min() {
        return array[0];
    }

    /**
     * Heap is empty if no element left, or no element was ever inserted.
     * @return true, if no element in heap
     */
    public boolean empty() {
        return array == null || array.length == 0;
    }

    /**
     * Return String representation of the heap as an array.
     * @return heap as array
     */
    public String toString(){
        return Arrays.toString(array);
    }
}
