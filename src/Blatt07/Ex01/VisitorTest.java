package Blatt07.Ex01;

import java.util.Objects;

/**
 * @author Mo Nipshagen (mnipshagen@uos.de)
 * @author Tobias Ludwig (toludwig@uos.de)
 *
 * Test class for Visitable MyList.
 */
public class VisitorTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
            list.advance();
        }

        CountingVisitor v0 = new CountingVisitor();
        list.accept(v0);
        // test if everything was traversed: 10 elements (1-10)
        int visits = v0.getIterations();
        assert visits == 10 : "Not all elements visited. Instead: " + visits;


        list.reset();
        for (int i = 0; i < 5; i++) {
            list.advance();
        }
        list.add(0); // insert a 0 as fifth element, 0 is a stop condition

        Visitor v1 = new Visitor(){
            /**
             * Stops visiting if it encounters an Integer == 0.
             */
            @Override
            public boolean visit(Object o) {
                if((Integer) o == 0)
                    return false;
                else return true;
            }
        };
        list.accept(v1);
        // test if stopped at 0: 6 elements (1,..., 5, 0)
        Integer lastVisited = list.getLastVisited();
        assert lastVisited == 0 : "Expected stopping at 0, instead at: " + lastVisited;


        MyList<String> stringList = new MyList<>();
        stringList.add("last"); // because we don't advance: LIFO
        stringList.add("");     // break condition
        stringList.add("first");
        Visitor v2 = new Visitor(){
            /**
             * Stops visiting if an empty String is encountered.
             */
            @Override
            public boolean visit(Object o) {
                if((String) o == "")
                    return false;
                else return true;
            }
        };
        stringList.accept(v2);
        // test if stopped at "": 2 elements ("first", "")
        String lastVisitedString = stringList.getLastVisited();
        assert lastVisitedString == "" : "Expected stopping at \"\", instead at: " + visits;

        System.out.println("Done.");
    }
}
