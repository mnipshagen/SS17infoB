package Blatt03.Ex03;

/**
 * Created by Mo on 03/05/2017.
 */
public class Volume extends Geometry implements Comparable {

    private Point p1,p2;

    public Volume(Point p1, Point p2) {
        super(p1.dimensions());
        if (p1.dimensions() != p2.dimensions()) {
            throw new RuntimeException("Cannot create Object with points of different dimensions");
        }
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public double volume() {
        double v = 1.;
        for (int i = 0; i < dimensions(); i++) {
            v *= Math.abs(p1.getCoordinates()[i] - p2.getCoordinates()[i]);
        }
        return v;
    }

    @Override
    public Geometry encapsulate(Geometry var1) {
        if (this.dimensions() != var1.dimensions()) {
            return null;
        }
        double[] x,x1,x2,x3 = null;
        x = p1.getCoordinates();
        x1 = p2.getCoordinates();
        if (var1 instanceof Point) {
            x2 = ((Point)var1).getCoordinates();
        } else if (var1 instanceof Volume) {
            x2 = ((Volume)var1).getP1().getCoordinates();
            x3 = ((Volume)var1).getP2().getCoordinates();
        } else {
            return null;
        }
        double[] max = new double[x.length], min = new double[x.length];
        for (int i = 0; i < x.length; i++) {
            if (x3 == null) {
                max[i] = Math.max(Math.max(x[i],x1[i]),x2[i]);
                min[i] = Math.min(Math.min(x[i],x1[i]),x2[i]);
            } else {
                max[i] = Math.max(Math.max(x[i], x1[i]), Math.max(x2[i], x3[i]));
                min[i] = Math.min(Math.min(x[i], x1[i]), Math.min(x2[i], x3[i]));
            }
        }
        return new Volume(new Point(max), new Point(min));
    }

    public Point getP1() {
        return p1;
    }

    public Point getP2() {
        return p2;
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
    public int compareTo(Object o) {
        if (o == null) {
            throw new NullPointerException("Cannot compare to null");
        }
        if (o instanceof Geometry) {
            return (int) (this.volume() - ((Geometry)o).volume());
        } else {
            throw new ClassCastException("o is not of type Geometry.");
        }
    }
}
