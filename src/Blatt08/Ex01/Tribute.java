package Blatt08.Ex01;

import java.util.Random;

/**
 * Created by nipsh on 15/06/2017.
 */
public class Tribute {

    private final static String[] NAMES = new String[] {"Sirena", "Kenyetta", "Marybelle", "Camille", "Tami", "Lakisha", "Tyron", "Ezra", "Nick", "Diann", "Danae", "Lyman", "Sol", "Dorathy", "Annamarie", "Joel", "Gennie", "Casandra", "Amparo", "Ira", "Raleigh", "Cherise", "Shery", "Shelba", "Hillary", "Josefine", "Vasiliki", "Gillian", "Loria", "Adriene", "Shaun", "Sindy", "Lenita", "Leatha", "Elfriede", "Marline", "Carmine", "Melani", "Rupert", "Daina", "Rosalina", "Candace", "Selina", "Vella", "Elmo", "Yasuko", "Nakisha", "Lore", "Sina", "Lauran"};
    private static boolean[] TAKEN = new boolean[NAMES.length];
    private static int counter = 0;

    private String name;
    private double x;
    private double y;

    public Tribute()
    {
        if (counter == NAMES.length)
        {
            name = getRandomString();
        } else {
            int pos;
            do {
                pos = (int) (Math.random() * NAMES.length);
                name = NAMES[pos];
            } while (TAKEN[pos]);
            TAKEN[pos] = true;
            counter ++;
        }
        x = y = 0.;
    }

    public Tribute(String n)
    {
        this.name = n;
        x = y = 0.;
        for (int i = 0; i < NAMES.length; i++) {
            if (NAMES[i].equals(n)){
                TAKEN[i] = true;
                counter ++;
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

    public void move(double dx, double dy)
    {
        this.x += dx;
        this.y += dy;
    }

    private String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();

    }
}
