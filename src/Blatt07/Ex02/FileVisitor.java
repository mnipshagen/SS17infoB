package Blatt07.Ex02;

import Blatt07.Ex01.Visitor;

import java.io.File;

/**
 * Extending the Visitor class trying to be all OO.
 * <p>
 * Classes that implement the interface Visitor assure that the implement the
 * method {@link #visit(Object)}. The method {@link #visit(Object)} will be
 * called by a {@link Visitable} instance for every Object it visits during one
 * call of the method {@link Visitable#accept(Visitor)}.
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @see Visitable
 */
public interface FileVisitor
        extends Visitor<File>
{

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
    boolean visit(File o);

    /**
     * For proper display of file trees we need to know at which depth we are
     *
     * @param o     the just visited File
     * @param depth current depth
     * @return {@link FileVisitorResult#SKIP} if file is not to be considered
     * {@link FileVisitorResult#CONTINUE} if file is to be considered
     * {@link FileVisitorResult#STOP} if this is the end of all things
     */
    FileVisitorResult visit(File o, int depth);

    /**
     * Applying a filter of file names to be skipped
     *
     * @param names the list of all names to be skipped
     */
    void applyFilter(String[] names);

    /**
     * whether or not to explore subdirectories
     *
     * @param recursive true for exploring!
     */
    void setRecursive(boolean recursive);

}
