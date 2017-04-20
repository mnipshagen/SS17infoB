package Blatt02.Ex01;

/**
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Boerse {
    public static void main(String [] args){
        Company oc = new Company("Oscorp");
        Company bl = new Company("Buy n' Large");
        Company mc = new Company("Mom Corp");
        Company sc = new Company("Soylent Cooperation");

        oc.changeStockPrice(243.5);
        bl.changeStockPrice(142.3);
        mc.changeStockPrice(123123.0);
        sc.changeStockPrice(5643.7);
        mc.changeStockPrice(50.3);

        bl = null;
        mc = null;
        System.gc();

        sc.changeStockPrice(8762.9);
        oc = null;
        sc.changeStockPrice(124.5);
        sc = null;
        System.gc();
    }
}
