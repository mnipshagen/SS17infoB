package Blatt01.Ex02;

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
        int max = 0;
        if(args.length != 0) {
            try{
                max = Integer.valueOf(args[0]);
            } catch (Exception e) {
                System.out.println("Eigentlich sollte eine Zahl als Argument " +
                        "uebergeben werden");
            }
        } else {
            System.out.println("Bis wohin soll Fibonacci'd werden?");
            Scanner sc = new Scanner(System.in);
            max = sc.nextInt();
        }
		Fibonacci f = new Fibonacci();
		print(f, max);
    }

    private static void print (Fibonacci f, int max) {
    	System.out.println("| n |  f(n)  |");
    	System.out.println("+---+--------+");
    	for (int i = 0; i <= max; i++) {
    		String n = String.format("%1$3s",String.valueOf(i));
    		String fn =
    			String.format("%1$8s",String.valueOf(f.next()));
	    	System.out.println("|" + n + "|" + fn + "|");
		}
    }
}
