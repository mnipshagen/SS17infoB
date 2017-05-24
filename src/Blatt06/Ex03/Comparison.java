package Blatt06.Ex03;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by nipsh on 23/05/2017.
 */
public class Comparison {

    private static final int NOOFREPS = 10;
    private static final String[] TESTVALUES = {"This","Is","A","TestString"};

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ArrayList al = new ArrayList();
        HashSet hs = new HashSet();

        long[][] results = testAll(ll, al, hs);
        String[] classes = {ll.getClass().getSimpleName(), al.getClass().getSimpleName(), hs.getClass().getSimpleName()};
        printResults(classes, results);

    }

    private static long[][] testAll(Collection... tests) {
        long[][] results = new long[3][];
        results[0] = testAdd(tests);
        results[1] = testContains(tests);
        results[2] = testRemove(tests);

        return results;
    }

    private static void printResults(String[] classes, long[][] results) {
        System.out.printf("%15s|%15s|%15s|%15s|%n","Classes","add()","contains()","remove()");
        System.out.print(String.format("%1$15s|%1$15s|%1$15s|%1$15s|%n","+").replace(" ","-"));
        for(int i = 0; i < classes.length; i ++) {
            Object[] a = new Object[results[i].length + 1];
            a[0] = classes[i];
            for(int j = 0; j < results[i].length; j++){
                a[j+1] = results[i][j];
            }
            System.out.printf("%15s|%12d ns|%12d ns|%12d ns|%n", a);
        }
    }

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
