package Blatt08.Ex01;

import java.util.Random;

/**
 * This class represents a tribute for the hunger games
 * Each tribute has some readable x & y coordinates to map them to the top down view of the arena
 * Each tribute also has a name
 * More is not needed. More is not relevant for the entertainment of the people
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Tribute
{
    // some random names to supply when none was given
    private final static String[] NAMES = new String[]{
            "Sirena", "Kenyetta", "Marybelle", "Camille", "Tami", "Lakisha", "Tyron", "Ezra", "Nick", "Diann", "Danae",
            "Lyman", "Sol", "Dorathy", "Annamarie", "Joel", "Gennie", "Casandra", "Amparo", "Ira", "Raleigh", "Cherise",
            "Shery", "Shelba", "Hillary", "Josefine", "Vasiliki", "Gillian", "Loria", "Adriene", "Shaun", "Sindy",
            "Lenita", "Leatha", "Elfriede", "Marline", "Carmine", "Melani", "Rupert", "Daina", "Rosalina", "Candace",
            "Selina", "Vella", "Elmo", "Yasuko", "Nakisha", "Lore", "Sina", "Lauran"};
    // making sure no name is used double
    private static boolean[] TAKEN = new boolean[NAMES.length];
    private static int counter = 0;

    private String name;
    private double x;
    private double y;

    public Tribute()
    {
        // no double names
        if (counter == NAMES.length)
        {
            name = getRandomString();
        }
        else //Random name!
        {
            int pos;
            do
            {
                pos = (int) (Math.random() * NAMES.length);
                name = NAMES[pos];
            } while (TAKEN[pos]);
            TAKEN[pos] = true;
            counter++;
        }
        x = y = 0.;
    }

    public Tribute(String n)
    {
        this.name = n;
        x = y = 0.;
        // no double names!
        for (int i = 0; i < NAMES.length; i++)
        {
            if (NAMES[i].equals(n))
            {
                TAKEN[i] = true;
                counter++;
                break;
            }
        }
    }

    public double getX()
    {
        return x;
    }

    public double getY()
    {
        return y;
    }

    public String getName()
    {
        return name;
    }

    /**
     * moves the tribute by the specified distance in x & y
     * @param dx x distance to move
     * @param dy y distance to move
     */
    public void move(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
    }

    /**
     * returns a string with length 10 made out of random letters
     * @return random string
     */
    private String getRandomString()
    {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();
        Random rnd = new Random();
        while (name.length() < 10)
        { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            name.append(chars.charAt(index));
        }
        return name.toString();

    }
}
