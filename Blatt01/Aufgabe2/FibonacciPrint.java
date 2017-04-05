import java.util.*;
import java.lang.*;

/**
 * Authors: mnipshagen, toludwig
 *
 * Takes an max input from user and prints out the Fibonacci sequence up
 * that maximum using the Fibonacci.java
 * Created by Mo on 04.04.2017.
 */
public class FibonacciPrint {

    public static void main(String[] args){
    	System.out.println("Bis wohin soll Fibonacci'd werden?");
    	Scanner sc = new Scanner(System.in);
    	int max = sc.nextInt();
    	print(max);
    }

    private static void print (int max) {
    	Fibonacci f = new Fibonacci();
    	System.out.println("| n |  f(n)  |");
    	System.out.println("+---+--------+");
    	for (int i = 0; i <= max; i++) {
    		String n = String.format("%1$3s",String.valueOf(i));
    		String fn =
    			String.format("%1$8s",String.valueOf(f.getFibonacci()));
	    	System.out.println("|" + n + "|" + fn + "|");
	    	f.next();
		}
    }
}
