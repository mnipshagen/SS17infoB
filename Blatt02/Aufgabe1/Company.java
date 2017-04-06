package Aufgabe1;

/**
 * Created by Jannik on 16.04.2016.
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
