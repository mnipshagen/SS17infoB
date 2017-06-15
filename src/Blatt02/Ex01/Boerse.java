package Blatt02.Ex01;

/**
 * A simple simulation of a stock market to test company and ticker
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Boerse
{

    public static void main(String[] args)
    {
        // some test companies
        Company oc = new Company("Oscorp");
        Company bl = new Company("Buy n' Large");
        Company mc = new Company("Mom Corp");
        Company sc = new Company("Soylent Cooperation");

        // give them some juicy juicy numbers: doubles and ints
        // also test if exception works
        try
        {
            oc.changeStockPrice(243.5);
            bl.changeStockPrice(142.3);
            mc.changeStockPrice(123123.0);
            sc.changeStockPrice(5643);
            mc.changeStockPrice(-50.3);
        }
        catch (Exception e)
        {
            System.err.print("Oh noes! An exception! It says: " + e.getMessage());
        }

        // time to go
        bl = null;
        mc = null;
        // explicit call to make it work. a bit.
        System.gc();

        // they are going down down down down with the sickness
        sc.changeStockPrice(8762.9);
        oc = null;
        sc.changeStockPrice(124.5);
        sc = null;
        // tidy up
        System.gc();
    }
}
