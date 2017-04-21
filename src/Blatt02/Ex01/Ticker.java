package Blatt02.Ex01;

/**
 * A ticker to output text on the console in the style of a stock market ticker
 * Works after the singleton principle to make sure only one instance handles
 * the output stream
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Ticker {
    private static Ticker instance;
    private Ticker (){}

    /**
     * Creates if necessary and returns the instance of the ticker
     * @return instance of ticker
     */
    public static Ticker getInstance(){
        if (instance == null){
            instance = new Ticker();
        }
        return instance;
    }

    /**
     * Removes all linebreaks to enforce single line representation and prints
     * the input text on the console using "+++" to distinguish all statements
     * @param text the text to print
     */
    public void print(String text){
        text = text.replace("\n","").replace("\r","");
        System.out.print("+++" + text);
    }
}
