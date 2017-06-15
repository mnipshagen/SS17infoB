package Blatt05.Ex03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Authors: Moritz Nipshagen, Tobias Ludwig
 * <p>
 * Test class for the typesafe Heap.
 */
public class HeapTest
{

    public static void main(String[] args)
    {
        // Test sorting for Integers
        Integer[] array = new Integer[]{3, 4, 3, 1, 8, 6};
        System.out.println("Sorting the Array: " + Arrays.toString(array));
        // first transform data into a heap
        Heap<Integer> heap_int = new Heap<Integer>();
        for (Integer a : array)
        {
            heap_int.insert(a);
        }
        sort(heap_int);

        //----------------------------------------------------------------
        // Now for Strings.
        // Testing with both, lexical sorting
        Heap<String> heap_lex = new Heap<String>();
        // and with sorting for word length
        Heap<String> heap_len = new Heap<String>(new Comparator<String>()
        {
            @Override
            public int compare(String s1, String s2)
            {
                return s1.length() - s2.length(); // return < 0, if s1 shorter than s2
            }
        });

        Heap[] heaps = new Heap[]{heap_lex, heap_len};

        int i = 0;
        for (Heap heap : heaps)
        {
            System.out.println(i == 0 ?
                                       "\nComparison alphabetically:\n==========================" :
                                       "\nComparison of word length:\n=========================="
            );
            heap.insert("David");
            heap.insert("Benjamin");
            heap.insert("Claudia");
            heap.insert("Abel");

            sort(heap);
            i++;
        }

    }

    /**
     * Kind of a heap sort. Iteratively prints and removes the minimum.
     */
    public static void sort(Heap heap)
    {
        System.out.println("Elements sorted ascendingly: ");
        while (!heap.empty())
        {
            System.out.println(heap.get_min() + ",\t Current Heap: " + heap.toString());
            try
            {
                heap.del_min();
            }
            catch (Exception e)
            {
            }
        }
    }
}

