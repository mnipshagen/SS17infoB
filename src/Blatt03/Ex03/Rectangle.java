package Blatt03.Ex03;

/**
 * A special case of the hyperrectangle which can only have 2 dimensions.
 * I guess it is helpful as it guarantees 2 dimensions if you are too
 * lazy too check?
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version probably final
 */
public class Rectangle extends Volume {

    /**
     * no explicit check for dimensions, since Point2D promises a 2D Point
     * A rectangle of 2D points is 2 dimensional
     * @param p1 one corner point
     * @param p2 the diagonally opposite point of p1 of the hyperrectangle
     */
    public Rectangle(Point2D p1, Point2D p2) {
        super(p1,p2);
    }
}
