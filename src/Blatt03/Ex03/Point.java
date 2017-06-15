package Blatt03.Ex03;

/**
 * A class that describes a real n-dimensional point
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version probably final
 */
public class Point
        extends Geometry
        implements Comparable
{

    /**
     * stores the n coordinates of the point
     */
    private double[] coordinates;

    /**
     * creates a point from a given array/list/variable amount of doubles
     * dimension is implicitly modelled by the length of the given array
     *
     * @param coords holds all the doubles
     */
    public Point(double... coords)
    {
        super(coords.length);
        this.coordinates = coords;
    }

    public Point(Point p)
    {
        super(p.dimensions());
        this.coordinates = p.getCoordinates();
    }

    /**
     * A point has a volume of 0
     *
     * @return the volume of the point
     */
    @Override
    public double volume()
    {
        return 0;
    }

    /**
     * provides a new geometry which completely encapsulates this and the
     * given var1 Geometry in a minimal way.
     * Realised by taking the min & max of
     * each single coordinate of all available points
     *
     * @param var1 the other geometry to encapsulate
     * @return a new volume which encapsulates this and var1
     */
    @Override
    public Geometry encapsulate(Geometry var1)
    {
        if (this.dimensions() != var1.dimensions())
        {
            return null;
        }
        if (var1 instanceof Point)
        {
            return new Volume(this, (Point) var1);
        }
        if (var1 instanceof Volume)
        {
            Volume v = (Volume) var1;
            double[] p1 = v.getP1().getCoordinates();
            double[] p2 = v.getP2().getCoordinates();

            double[] pMax, pMin;
            pMin = new double[coordinates.length];
            pMax = new double[coordinates.length];
            for (int i = 0; i < coordinates.length; i++)
            {
                double x, x1, x2;
                x = coordinates[i];
                x1 = p1[i];
                x2 = p2[i];
                pMin[i] = Math.min(Math.min(x, x1), x2);
                pMax[i] = Math.max(Math.max(x, x1), x2);
            }
            return new Volume(new Point(pMax), new Point(pMin));
        }
        return null;
    }

    /**
     * returns a copy of the coordinates of the point
     * only a copy to not have an implicit way of changing the data
     *
     * @return the coordinate array of the point
     */
    public double[] getCoordinates()
    {
        double[] copy = new double[coordinates.length];
        System.arraycopy(coordinates, 0, copy, 0, copy.length);
        return copy;
    }

    /**
     * Compares this object with the specified object for order.  Returns a
     * negative integer, zero, or a positive integer as this object is less
     * than, equal to, or greater than the specified object.
     * <p>
     * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
     * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
     * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
     * <tt>y.compareTo(x)</tt> throws an exception.)
     * <p>
     * <p>The implementor must also ensure that the relation is transitive:
     * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
     * <tt>x.compareTo(z)&gt;0</tt>.
     * <p>
     * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
     * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
     * all <tt>z</tt>.
     * <p>
     * <p>It is strongly recommended, but <i>not</i> strictly required that
     * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
     * class that implements the <tt>Comparable</tt> interface and violates
     * this condition should clearly indicate this fact.  The recommended
     * language is "Note: this class has a natural ordering that is
     * inconsistent with equals."
     * <p>
     * <p>In the foregoing description, the notation
     * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
     * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
     * <tt>0</tt>, or <tt>1</tt> according to whether the value of
     * <i>expression</i> is negative, zero or positive.
     *
     * @param o the object to be compared.
     * @return a negative integer, zero, or a positive integer as this object
     * is less than, equal to, or greater than the specified object.
     * @throws NullPointerException if the specified object is null
     * @throws ClassCastException   if the specified object's type prevents it
     *                              from being compared to this object.
     */
    @Override
    public int compareTo(Object o)
    {
        if (o == null)
        {
            throw new NullPointerException("Cannot compare to null");
        }
        if (o instanceof Geometry)
        {
            if (this.volume() < ((Geometry) o).volume())
            {
                return -1;
            }
            else if (this.volume() > ((Geometry) o).volume())
            {
                return 1;
            }
            else return 0;
        }
        else
        {
            throw new ClassCastException("o is not of type Geometry.");
        }
    }
}
