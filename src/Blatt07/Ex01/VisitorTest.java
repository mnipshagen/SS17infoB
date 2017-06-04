package Blatt07.Ex01;

/**
 * @author Mo Nipshagen (mnipshagen@uos.de)
 * @author Tobias Ludwig (toludwig@uos.de)
 *
 * Test class for MyListVisitor.
 */
public class VisitorTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
            list.advance();
        }

        MyListVisitor visitor = new MyListVisitor();
        list.accept(visitor);
        // test if everything was traversed: 10 elements (1-10)
        int visits = visitor.getIterations();
        assert visitor.getIterations() == 10 : "Not all elements visited. Instead: " + visits;


        list.reset();
        for (int i = 0; i < 5; i++) {
            list.advance();
        }
        list.add(0); // insert a 0 as fifth element, 0 is a stop condition

        MyListVisitor v1 = new MyListVisitor();
        list.accept(v1);
        // test if stopped at 0: 6 elements (1,..., 5, 0)
        visits = v1.getIterations();
        assert visits == 6 : "Expected 6 elements to be visited, instead: " + visits;


        MyList<String> stringList = new MyList<>();
        stringList.add("last"); // because we don't advance: LIFO
        stringList.add("");     // break condition
        stringList.add("first");
        MyListVisitor v2 = new MyListVisitor();
        stringList.accept(v2);
        // test if stopped at "": 2 elements ("first", "")
        visits = v2.getIterations();
        assert visits == 2 : "Expected 2 elements to be visited, instead: " + visits;

        System.out.println("Done.");
    }
}
