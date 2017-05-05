package Blatt03.Ex03;

/**
 * A special case of the Point which can only have 2 dimensions.
 * I guess it is helpful as it guarantees 2 dimensions if you are too
 * lazy too check?
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version probably final
 */
public class Point2D extends Point{

    /**
     * Takes an array of doubles that represent the coordinates of the point
     * The array has to have exactly two entries though
     * @param coords the two coordinates of the point
     * @throws RuntimeException if coords does not hold exactly two entries
     */
    public Point2D(double[] coords) {
        super(coords);
        if (coords.length != 2) {
            throw new RuntimeException("Must be initialised with exactly two coordinates.");
        }
    }

    /**
     * takes the two coordinates of the point as single arguments
     * no check for length necessary as we will always have exactly two
     * @param x the first coordinate
     * @param y the second coordinate
     */
    public Point2D(double x, double y) {
        super(x,y);
    }
}
