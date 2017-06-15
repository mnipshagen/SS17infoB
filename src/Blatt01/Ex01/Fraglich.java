package Blatt01.Ex01;

public class Fraglich
{

    static int a;

    public static void main(String[] args)
    {
        int a = 5;
        int[] c = {16, 4, 2, 9};

        /*** Stelle 1 ***/

        initialize();

        /*** Stelle 3 ***/

        for (int b = -1; b < 1; b += 3)
        {

            /*** Stelle 4 ***/
            a /= 2;
            c[b + 2] -= c[b + 2];
        }

        /*** Stelle 5 ***/

        for (a = 2; a < 3; a++)
        {

            int b = 2;
            c[a] -= c[b];

            /*** Stelle 6 ***/
        }

        /*** Stelle 7 ***/

        int b = method(a + c[a - 3]);

        /*** Stelle 9 ***/

        b = 7 + method(++a, c);

        /*** Stelle 11 ***/

        a = method(method(method(a), new int[]{b, a, c[0], c[2], c[3], c[1]}));

        /*** Stelle 15 ***/
    }

    static void initialize()
    {

        int b = 0;

        a = 23;

        /*** Stelle 2 ***/
    }

    protected static int method(int b)
    {

        /*** Stelle 8, 12 bzw. 14 ***/

        return a;
    }

    private static int method(int a, int[] c)
    {

        a++;
        c[0] /= 2;
        c[0] = c[0] + c[1];
        c = new int[4];

        /*** Stelle 10 bzw. 13 ***/

        return a;
    }

}
