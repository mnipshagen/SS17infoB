package Blatt07.Ex02;

import Blatt07.util.Visitor;
import java.io.File;

/**
 * Created by Mo on 04/06/2017.
 */
public class fileVisitor implements Visitor<File> {
    /**
     * Called by the method {@link Visitable#accept(Visitor)} for every element it
     * visits. The visiting can be stopped by returning <code>false</code>.
     *
     * @param o the element that has just been visited by
     *          {@link Visitable#accept(Visitor)}
     * @return <code>true</code> if the visit should be continued until every
     * element in a {@link Visitable} has been visited once, else
     * <code>false</code>
     */
    @Override
    public boolean visit(File o) {
        return false;
    }
}
