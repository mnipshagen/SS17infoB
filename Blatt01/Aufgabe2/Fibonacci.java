/**
 * Authors: mnipshagen, toludwig
 *
 * A class that can iterate throgh the Fibonacci sequence.
 * Created by Mo on 04.04.2017.
 */
public class Fibonacci {

	private int previous;
	private int current;

    public Fibonacci () {
        this.previous = 0;
        this.current = 1;
    }

    public void next(){
        int tmp = this.previous + this.current;
        this.previous = this.current;
        this.current  = tmp;
    }

    public int getFibonacci(){
    	return current;
    }
}
