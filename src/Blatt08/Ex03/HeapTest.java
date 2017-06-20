package Blatt08.Ex03;


/**
 * Test the class Heap if it functions as it should.
 *
 * @author Mo Nipshagen, Tobias Ludwig
 */
public class HeapTest {

    public static void main(String[] args) {

        Heap<Integer> heap = new Heap<Integer>();
        final Integer[] sortedIntegers = {-1, 0, 1, 2, 3, 5, 7};

        for (Integer i : sortedIntegers)
            heap.insert(i);

        final String FILENAME = "./src/Blatt08/ser/test.ser";
        heap.serialize(FILENAME);

        Heap heap2 = Heap.deserialize(FILENAME);
        // check if this one is equal to the original one
        // since the elements were ordered they still have to be
        for (Integer i : sortedIntegers) {
            Integer h2 = (Integer) heap2.deleteFirst();
            if (h2 != (int)i)
                System.out.printf("Mistake at element %d\n", i);
        }

        System.out.println("Done.");
    }


}
