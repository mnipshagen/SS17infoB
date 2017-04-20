package Blatt02.Ex01;

/**
 * Authors: mnipshagen, toludwig
 */
public class Ticker {
    private static Ticker ticker;
    private Ticker(){};

    public static Ticker getInstance(){
        if (ticker == null){
            ticker = new Ticker();
        }
        return ticker;
    }
    public void print(String text){
        text = text.replace("\n", "");
        System.out.print("+++" + text);
    }
}
