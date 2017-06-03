package Blatt06.Ex04;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * For Testing the MyList fast-fail Iterator.
 *
 * @author Tobias Ludwig (toludwig@uos.de)
 * @author Mo Nipshagen  (mnipshagen@uos.de)
 */
public class IteratorTest {
    public static void main(String[] args) {
        MyList<Integer> l = new MyList<>();
        for (Integer i = 0; i < 10; i++) {
            l.add(i);
            l.advance();
        }

        /*
         * Testing whether iteration works
         */
        int count = 0, elem = 0;
        Iterator<Integer> it = l.iterator();
        while (it.hasNext()) {
            elem = it.next();
            count++;
            assert elem == count : "Error while iterating";
        }

        /*
         * Testing whether fail-fast works
         */
        l.reset();
        it = l.iterator();
        it.next();
        l.delete();
        try {
            Integer el = it.next();
            System.out.println("Should not be possible to iterate further after add-operation: " + el);
        } catch (ConcurrentModificationException ignored) {
        }

        it = l.iterator();
        l.add(3);
        try {
            it.remove();
            System.out.println("Should not be possible to iterate further after add-operation: ");
        } catch (ConcurrentModificationException ignored) {
        }


        /*
         * Testing remove of the iterator.
         */
        MyList<Integer> removeList = new MyList<>();
        for (Integer i = 0; i < 10; i++){
            removeList.add(i);
            removeList.advance();
        }
        it = removeList.iterator();
        it.next(); // element 1
        it.remove(); // remove 2
        assert it.next() == 3 : "Failed to remove element from list";
    }
}
