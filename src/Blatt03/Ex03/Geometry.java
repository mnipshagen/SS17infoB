package Blatt03.Ex03;

/**
 * Created by Mo on 03/05/2017.
 */
public abstract class Geometry
{
    private int dimension;

    public Geometry(int dimension)
    {
        if (dimension < 2)
        {
            throw new RuntimeException("dimension is < 2");
        }
        else
        {
            this.dimension = dimension;
        }
    }

    public int dimensions()
    {
        return this.dimension;
    }

    public abstract double volume();

    public abstract Geometry encapsulate(Geometry var1);
}