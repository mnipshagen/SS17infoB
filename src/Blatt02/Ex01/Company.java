package Blatt02.Ex01;

/**
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Company {
    private String name;
    private static Ticker ticker = Ticker.getInstance();

    public Company(String name){
        this.name = name;
    }
    public void changeStockPrice(double d){
        ticker.print(this.name + " " +d);
    }

    protected void finalize(){
        Ticker.getInstance().print(this.name + " is insolvent");
    }
}
