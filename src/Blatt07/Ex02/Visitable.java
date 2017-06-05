package Blatt07.Ex02;

import Blatt07.Ex01.Visitor;

/**
 * Classes implementing the Visitable interface assure that they accept a
 * FileVisitor and show that FileVisitor every element hold by this Visitable. For
 * example a List, which implements the Visitable interface would traverse
 * through every element after its {@link #accept(Visitor)} method has been
 * called. It then calls for every of its elements {@link FileVisitor#visit(Object)}
 * .
 * 
 * Thus, the {@link Visitable} interface is together with the {@link FileVisitor}
 * interface another way to iterate through a group of elements.
 * 
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 * 
 * @param <E>
 *           type of the elements which will be visited
 * 
 * @see FileVisitor
 */
public interface Visitable<E> {

   /**
    * Iterates through every element of this instance and calls for every
    * element {@link FileVisitor#visit(Object)}. Stops visiting every element if
    * there are no more elements to be visited or if
    * {@link FileVisitor#visit(Object)} returns <code>false</code>
    * 
    * @param v
    *           the FileVisitor which should be called for every element in this
    *           Visitable instance
    */
   void accept(Visitor<E> v);

}
