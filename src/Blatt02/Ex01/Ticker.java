package Blatt02.Ex01;

/**
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Ticker {
    private static Ticker instance;
    private Ticker (){}

    public static Ticker getInstance(){
        if (instance == null){
            instance = new Ticker();
        }
        return instance;
    }
    public void print(String text){
        text = text.replace("\n","").replace("\r","");
        System.out.print("+++" + text);
    }
}
