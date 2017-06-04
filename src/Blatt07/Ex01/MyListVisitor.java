package Blatt07.Ex01;

import Blatt07.util.Visitable;
import Blatt07.util.Visitor;

/**
 * @author Mo Nipshagen (mnipshagen@uos.de)
 * @author Tobias Ludwig (toludwig@uos.de)
 *
 * A Visitor for the MyList.
 */
public class MyListVisitor implements Visitor {

    /**
     * counts the iterations while traversing
     */
    private int iterations = 0;

    /**
     * Checks whether the Object is an Integer of value 0
     * or an empty String and returns false in either case.
     * This causes the calling {@link Visitable#accept(Visitor)}
     * to cancel traversing the list. Otherwise, it returns true.
     * @param o the element that has just been visited by
     *           {@link Visitable#accept(Visitor)}
     * @return <code>true</code> if the visit should be continued until every
     *         element in a {@link Visitable} has been visited once, else
     *         <code>false</code>
     */
    @Override
    public boolean visit(Object o) {
        iterations++;

        if(o instanceof Integer)
            return ((Integer) o) != 0;
        else if(o instanceof String)
            return ((String) o) != "";
        else return true;
    }

    /**
     * Iterations are the number of visits. The iteration
     * in which {@link Visitor#visit(Object)} returns false
     * is still counted.
     * @return the number of visits the Visitor has done
     */
    public int getIterations() {
        return iterations;
    }
}
