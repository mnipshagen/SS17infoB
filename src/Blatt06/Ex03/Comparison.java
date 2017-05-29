package Blatt06.Ex03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * This class tests different implementations of the interface {@link Collection} in regard of the methods
 * {@link Collection#add(Object)}, {@link Collection#contains(Object)} and {@link Collection#remove(Object)}.
 * As of version <code>1.0</code> three implementations are tested:
 * {@link LinkedList}, {@link ArrayList} and {@link HashSet}
 * Other implementations of Collection can easily be added:
 *      Just add a <code>new Class()</code> statement into the array <code>c</code>
 *
 * If it is of concern to have more repetitions to test to get a better average, {@link #NOOFREPS} can be changed for that
 * If other Objects are to be tested: The variable {@link #TESTVALUES} can be changed to any array type extending from {@link Object}
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version 1.1
 */
public class Comparison {

    // amount of repetitions to be done per class per test
    private static final int NOOFREPS = 1000;
    // the values to be tested
    private static final String[] TESTVALUES = {"This","Is","A","TestString"};

    public static void main(String[] args) {
        Collection[] c = {
                new LinkedList(),
                new ArrayList(),
                new HashSet()
        };

        long[][] results = testAll(c);
        String[] classes = new String[results[0].length];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = c[i].getClass().getSimpleName();
        }

        printResults(classes, results);

    }

    /**
     * Calls all test methods implemented and saves the result of each in an array and then gives back a 2D array
     * holding all results for one test
     * @param tests the Classes to be tested
     * @return an array where the first dimension corresponds to the test done and the second to the corresponding results
     */
    private static long[][] testAll(Collection... tests) {
        long[][] results = new long[3][];
        results[0] = testAdd(tests);
        results[1] = testContains(tests);
        results[2] = testRemove(tests);

        return results;
    }

    /**
     * prints all results in a nicely formatted table
     * @param classes the classes that the tests were done on
     * @param results the results of the tests
     */
    private static void printResults(String[] classes, long[][] results) {
        if (classes.length != results[0].length) {
            throw new RuntimeException("Number of classes mismatched to number of results");
        }
        System.out.printf("%-15s|%15s|%15s|%15s|%n","Classes","add()","contains()","remove()");
        System.out.print(String.format("%1$15s|%1$15s|%1$15s|%1$15s|%n","+").replace(" ","-"));
        for(int i = 0; i < classes.length; i ++) {
            Object[] a = new Object[results[i].length + 1];
            a[0] = classes[i];
            for(int j = 0; j < results[i].length; j++){
                a[j+1] = results[i][j];
            }
            System.out.printf("%-15s|%12d ns|%12d ns|%12d ns|%n", a);
        }
    }

    /**
     * Evaluates the average timed needed to insert an Object to a Collection
     * Average taken over: {@link #NOOFREPS} times taken the average over all elements in {@link #TESTVALUES}
     * @param tests the classes to be tested
     * @return resulting average times
     */
    private static long[] testAdd(Collection... tests) {
        long[] result = new long[tests.length];

        for (int i = 0; i < tests.length; i++) {
            Collection c = tests[i];
            long res = 0;

            for (int n = 0; n < NOOFREPS; n++) {
                long time = 0;

                for (String s :
                        TESTVALUES) {
                    long tmp = System.nanoTime();
                    //noinspection unchecked
                    c.add(s);
                    time += System.nanoTime() - tmp;
                }
                res += time/TESTVALUES.length;
            }
            res /= NOOFREPS;
            result[i] = res;
        }
        return result;
    }

    /**
     * Evaluates the average timed needed to find an Object to a Collection
     * Average taken over: {@link #NOOFREPS} times taken the average over all elements in {@link #TESTVALUES}
     * plus a string not contained in the collection
     * @param tests the classes to be tested
     * @return resulting average times
     */
    private static long[] testContains(Collection... tests) {
        long[] result = new long[tests.length];

        for (int i = 0; i < tests.length; i++) {
            Collection c = tests[i];
            long res = 0;

            for (int n = 0; n < NOOFREPS; n++) {
                long time = 0;

                for (String s :
                        TESTVALUES) {
                    long tmp = System.nanoTime();
                    c.contains(s);
                    time += System.nanoTime() - tmp;
                }
                c.contains("CANNOT FIND ME");
                res += time/(TESTVALUES.length + 1);
            }
            res /= NOOFREPS;
            result[i] = res;
        }
        return result;
    }

    /**
     * Evaluates the average timed needed to remove an Object to a Collection
     * Average taken over: {@link #NOOFREPS} times taken the average over all elements in {@link #TESTVALUES}
     * plus a string not contained in the collection
     * @param tests the classes to be tested
     * @return resulting average times
     */
    private static long[] testRemove(Collection... tests) {
        long[] result = new long[tests.length];

        for (int i = 0; i < tests.length; i++) {
            Collection c = tests[i];
            long res = 0;

            for (int n = 0; n < NOOFREPS; n++) {
                long time = 0;

                for (String s :
                        TESTVALUES) {
                    long tmp = System.nanoTime();
                    c.remove(s);
                    time += System.nanoTime() - tmp;
                }
                c.remove("CANNOT FIND ME");
                res += time/(TESTVALUES.length + 1);
            }
            res /= NOOFREPS;
            result[i] = res;
        }
        return result;
    }


}
