package Blatt08.Ex02;

import java.io.*;
import java.util.HashMap;

/**
 * Class to calculate the Fibonacci number. Uses a HashMap to reduce the
 * calculation cost.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 *
 */
public class Fibonacci {

    private static HashMap<Integer, Long> fibonacciHash = null;

    private final static String FILENAME = "./src/Blatt08/ser/fibonacci.ser";
    private final static File serFile = new File(FILENAME);

    /**
     * Fill HashMap with initial key-value-pairs.
     */
    static {
        // if we run this for the very first time, i.e.
        // serFile does not exist
        // or serFile is empty
        if(!serFile.exists() || serFile.length() == 0) {
            // initialise manually
            fibonacciHash = new HashMap<>();
            fibonacciHash.put(0, 0L);
            fibonacciHash.put(1, 1L);
            // and create the file
            try {
                serFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Could not create file.");
            }
        } else {
            // if serFile has a earlier HashMap stored
            // retrieve this
            deserialize();
        }
    }

    /**
     * Deserializes and restores the HashMap.
     */
    private static void deserialize(){
        try(FileInputStream fis = new FileInputStream(serFile); ObjectInputStream ois = new ObjectInputStream(fis)) {
            fibonacciHash = (HashMap<Integer, Long>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("Could not deserialize.");
        }
    }

    /**
     * Serializes the HashMap.
     */
    private static void serialize() {
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(serFile, false))){
            oos.writeObject(fibonacciHash);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates the fibonacci value of n.
     *
     * @param n
     *           a natural number >= 0 to calculate the fibonacci value of
     *
     * @return fibonacci value of n
     */
    public static long fibonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n = " + n);
        }
        return getFibonacci(n);
    }

    private static long getFibonacci(int n) {
        if (fibonacciHash.containsKey(n)) {
            return fibonacciHash.get(n);
        } else {
            long nMinus1 = getFibonacci(n - 1);
            long nMinus2 = getFibonacci(n - 2);
            long fibonacci = nMinus1 + nMinus2;

            fibonacciHash.put(n, fibonacci);
            return fibonacci;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            printUsage();
        }
        try {
            Long before = System.nanoTime();
            System.out.println(fibonacci(Integer.parseInt(args[0])));
            System.out.printf("Took me " + ((System.nanoTime() - before)/1000000.0) + " milliseconds.");

        } catch (IllegalArgumentException ex) {
            printUsage();
        } finally {
            serialize();
        }
    }

    private static void printUsage() {
        System.out.println("java Fibonacci n");
        System.out.println("n must be a natural number >= 0");
    }

}
