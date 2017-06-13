package Blatt07.Ex03;

/**
 * Created by nipsh on 13/06/2017.
 */
public class RAI_Test {

    public static void main(String[] args) {
        Integer[] a = new Integer[10];
        for (int i = 0; i < a.length; i++) {
            a[i] = i * i;
        }

        RandomAccessInts ri = new RandomAccessInts(a, "array");
        ri.close();
    }
}
