package Blatt03.Ex03;

/**
 * Created by Mo on 03/05/2017.
 */
public class Point2D extends Point{

    public Point2D(double[] coords) {
        super(coords);
        if (coords.length != 2) {
            throw new RuntimeException("Must be initialised with exactly two coordinates.");
        }
    }
}
