package Blatt08.Ex01;

/**
 * This class represent the HungerGames arena of the 75th HungerGames.
 * The arena ins question has a diameter of 3 miles, but this may be altered with {@link #r}
 * A singleton approached was used, as there is only one arena at a time.
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Arena
{
    private static Arena instance = new Arena();
    private double r = 1.5;

    public static Arena getInstance()
    {
        return instance;
    }

    private Arena()
    {
    }

    /**
     * Given two coordinates, returns the sector in which these coordinates lay
     * There are 12 evenly spread sectors overall + the centre + outside of the arena
     * Each Sector covers a 30 degree angle. Sector 1 starts at the line from (0,0) to (0,1.5)
     * Canonical, the centre of the arena was an island, as such it is treated independently from the other sectors
     *
     * @param x the cartesian x-coordinate
     * @param y the cartesian y-coordinate
     * @return Sector the coordinates are in or 0 for the centre or -1 for outside
     */
    public int getArea(double x, double y)
    {   // if the length of the vector is bigger than the radius of the arena, the coordinates are in no sector
        if (Math.sqrt(x * x + y * y) > r)
        {
            return -1;
        }
        // did we just arrive on the island?
        if (x == 0 && y == 0)
        {
            return 0;
        }
        // convenience
        double pi6 = Math.PI / 6.0;
        // calculating angle between vector and x-axis.
        // shift by -pi/2 since the y-axis is our 0 line and not the x axis
        // positive angle, counter-clockwise
        double theta = Math.atan2(y, x) - (Math.PI / 2.0);

        int area;
        // if angle is negative...
        if (theta <= 0)
        {
            // ...start counting up for each 30 degrees we can subtract until we cross the 0 line
            area = 0;
            while (theta <= 0)
            {
                theta += pi6;
                area++;
            }
        }
        else // angle is positive...
        {
            // ...start counting down for each 30 degrees we can subtract
            area = 13;
            while (theta > 0)
            {
                theta -= pi6;
                area--;
            }
        }
        return area;
    }
}
