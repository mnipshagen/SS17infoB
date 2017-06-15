package Blatt01.Ex02;

import java.util.Scanner;

/**
 * Authors: mnipshagen, toludwig
 * <p>
 * Takes an max input from user and prints out the Fibonacci sequence up
 * that maximum using the Fibonacci.java
 * Created by Mo on 04.04.2017.
 */
public class FibonacciPrint
{

    public static void main(String[] args)
    {
        int max = 0;
        if (args.length != 0)
        {
            try
            {
                max = Integer.valueOf(args[0]);
            }
            catch (Exception e)
            {
                System.out.println("Eigentlich sollte eine Zahl als Argument " +
                                           "uebergeben werden");
            }
        }
        else
        {
            System.out.println("Bis wohin soll Fibonacci'd werden?");
            Scanner sc = new Scanner(System.in);
            max = sc.nextInt();
        }
        if (max < 0)
        {
            throw new IllegalArgumentException("Negative Iterationen sind eher schwierig tbh.");
        }
        Fibonacci f = new Fibonacci();
        print(f, max);
    }

    /**
     * dedizierte print methode um die main methode übersichtlich zu halten
     * gibt die nächsten {@code max} Werte der Fibonacci Sequenz von {@code f}
     *
     * @param f   Die Fibonacci klasse über die iteriert wird
     * @param max wie viele iterationen durchgeführt werden sollen
     */
    private static void print(Fibonacci f, int max)
    {
        max -= 2;

        System.out.println("| n |  f(n)  |");
        System.out.println("+---+--------+");
        System.out.println("|  0|       0|");
        if (max > 0)
        {
            System.out.println("|  1|       1|");
        }

        for (int i = 0; i <= max; i++)
        {
            String n = String.format("%1$3s", String.valueOf(i + 2));
            String fn =
                    String.format("%1$8s", String.valueOf(f.next()));
            System.out.println("|" + n + "|" + fn + "|");
        }
    }
}
