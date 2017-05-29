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
        for(Integer i=0; i<10; i++)
            l.add(i);

        int count = 0;

        // for(Integer i : l) // why is this not working???

        Iterator<Integer> it = l.iterator();
        while(it.hasNext()){
            it.next();
            count++;
        }
        assert count == 10 : "Not correct amount of iterations: " + count;

        it = l.iterator();
        it.next();
        l.delete();
        try {
            Integer el = (Integer) it.next();
            System.out.println("Should not be possible to iterate further after add-operation: " + el);
        } catch(ConcurrentModificationException ignored){ }
        it = l.iterator();

        l.add(3);
        try {
            Integer el = (Integer) it.next();
            System.out.println("Should not be possible to iterate further after add-operation: " + el);
        } catch(ConcurrentModificationException ignored){ }


        MyList<Integer> removeList = new MyList<>();
        for(Integer i=0; i<10; i++)
            removeList.add(i);
        it = removeList.iterator();
        it.remove();
        assert it.next() == 1 : "Failed to remove element from list";
    }
}
