package Blatt02.Ex01;

/**
 * Created by Jannik on 16.04.2016.
 */
public class Ticker {
    private static Ticker ticker;
    private Ticker (){};

    public static Ticker getInstance(){
        if (ticker == null){
            ticker = new Ticker();
        }
        return ticker;
    }
    public void print(String text){
        System.out.print("+++" + text);
    }
}
