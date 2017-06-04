package Blatt07.Ex02;

import Blatt07.util.Visitable;
import Blatt07.util.Visitor;

import java.io.File;

/**
 * Created by Mo on 04/06/2017.
 */
public class filesys implements Visitable<File>{
    /**
     * Iterates through every element of this instance and calls for every
     * element {@link Visitor#visit(Object)}. Stops visiting every element if
     * there are no more elements to be visited or if
     * {@link Visitor#visit(Object)} returns <code>false</code>
     *
     * @param v the Visitor which should be called for every element in this
     *          Visitable instance
     */
    @Override
    public void accept(Visitor<File> v) {

    }
}
