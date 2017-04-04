/**
 * A class that can iterate throgh the Fibonacci sequence.
 * Created by Mo on 04.04.2017.
 */
public class Fibonacci {

	private int previous;
	private int current;

    public fibonacci () {
        this.previous = 0;
        this.current = 1;
    }

    public void next(){
        int next = this.previous + this.current;
        this.previous = this.current;
        this.now = next;
    }

    public int getFibonacci(){
    	return current;
    }
}
