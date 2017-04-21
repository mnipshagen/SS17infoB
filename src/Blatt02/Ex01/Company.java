package Blatt02.Ex01;

/**
 * Represents a simple company on the stock market.
 * For that it only needs a name. All companies on the stock market
 * share a single ticker that handles their respective changes in price
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Company {
    private String name;
    private static Ticker ticker = Ticker.getInstance();

    /**
     * creates a company!
     * @param name the name of the company
     */
    public Company(String name){
        this.name = name;
    }

    /**
     * prints the new stock price of the company using the ticker
     * @param d the new stock price
     * @throws IllegalArgumentException if stock price is about to go negative
     */
    public void changeStockPrice(double d){
        if (d < 0) {
            throw new IllegalArgumentException("There are no negative stock values");
        }
        ticker.print(this.name + " " +d);
    }

    /**
     * When a company goes insolvent (is destroyed) it will announce it
     */
    protected void finalize(){
        Ticker.getInstance().print(this.name + " is insolvent");
    }
}
