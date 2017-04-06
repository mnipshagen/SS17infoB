package Blatt02.Ex01;

/**
 * Created by Jannik on 16.04.2016.
 */
public class Boerse {
    public static void main(String [] args){
        Company lc = new Company("LexCorp");
        Company md = new Company("Massive Dynamics");
        Company si = new Company("Stark Industries");

        si.changeStockPrice(528.0);
        md.changeStockPrice(491.0);
        lc = null;
        System.gc();
    }
}
