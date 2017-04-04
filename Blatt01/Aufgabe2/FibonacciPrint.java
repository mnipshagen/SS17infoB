/**
 * Takes an max input from user and prints out the Fibonacci sequence up
 * that maximum using the Fibonacci.java
 * Created by Mo on 04.04.2017.
 */
public class FibonacciPrint {

    public static void main(String[] args){
    	System.out.print("Bis wohin soll Fibonacci'd werden?");
    	Scanner sc = new Scanner(System.in);
    	int max = sc.nextInt();
    	print(max);
    }

    private void print (int max) {
    	Fibonacci f = new Fibonacci();
    	for (int i = 0; i <= max; i++) {
	    	System.out.print("Iteration " + i +": " + f.getFibonacci());
	    	f.next();
	    }
    }
}
