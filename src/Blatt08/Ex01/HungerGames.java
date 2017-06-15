package Blatt08.Ex01;

/**
 * A class attempting to satisfiably test {@link Arena} & {@link Tribute}
 * A proper test is not realisable, as we would have to test all possible points (they are infinitely) in a 2-D plane.
 * Instead we are testing each sector of the Arena once, and make sure to test some edge cases, like the outer circle
 * or the border between two areas.
 */
public class HungerGames
{

    public static void main(String[] args)
    {
        Tribute[] t = new Tribute[24];
        for (int i = 0; i < 24; i++)
        {
            t[i] = new Tribute();
        }
        Arena a = Arena.getInstance();

        t[0].move(2, 2);
        t[1].move(0, 1.5);
        t[2].move(0.75, 0.75);
        t[3].move(1.3, 0.2);
        t[4].move(1.3, -0.2);
        t[5].move(0.75, -0.75);
        t[6].move(0.1, -1.4);
        t[7].move(-0.1, -1.4);
        t[8].move(-0.75, -0.75);
        t[9].move(-1.3, -0.2);
        t[10].move(-1.3, 0.2);
        t[11].move(-0.75, 0.75);
        t[12].move(-0.1, 1.3);

        for (int i = 0; i < 13; i++)
        {
            String s = String.format("Tribute %4d %-10s is at coordinates x: % 4.2f ; y: % 4.2f, putting her in sector %2d", i, t[i].getName(), t[i].getX(), t[i].getY(), a.getArea(t[i].getX(), t[i].getY()));
            System.out.println(s);
            System.out.println(String.format("    The angle would be: % 4.2f", (Math.atan2(t[i].getY(), t[i].getX()) - Math.PI / 2)));
        }
    }
}
