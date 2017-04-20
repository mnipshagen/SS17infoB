package Blatt02.Ex01;

/**
 * Authors: mnipshagen, toludwig
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
