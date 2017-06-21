package Blatt09.Ex02;

import Blatt09.Ex02.AntField.Field;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * An {@code Ant} is created at a specific position of an {@link AntField} with
 * an initial {@code stepCount}. When running an Ant, it will lookup the values
 * on the current and all surrounding {@link Field}
 * (Moore-neighborhood) instances and test if the position is free, i.e. has a
 * value of {@code 0}, or if the value is greater than the {@code stepCount} of
 * this Ant. For both cases, the Ant will set the value of the {@code Field} at
 * the visited position to its own {@code stepCount+1}. After an {@code Ant} has
 * successfully visited one field, it will create new {@code Ant} instances with
 * an incremented {@code stepCount} to visit the other available {@code Field}
 * elements. The Ant will run until it finds no more {@code Field} elements in
 * its neighborhood to be altered.
 *
 * @author Mathias Menninghaus (mathias.menninghaus@uos.de)
 */
public class Ant implements Runnable {
    /**
     * This static pool is responsible to schedule our might ant workforce
     */
    public static final ExecutorService pool = Executors.newCachedThreadPool();
    /**
     * Since Java does even in version 9 has no f*cking tuple class... (WTF Oracle?!)
     * No, arrays and collections are only semi good replacements.
     * The delta x & y to the moore neighbour fields
     */
    private static final Pair[] moores = {
            new Pair(1, 1),
            new Pair(1, -1),
            new Pair(1, 0),
            new Pair(-1, 1),
            new Pair(-1, -1),
            new Pair(-1, 0),
            new Pair(0, 1),
            new Pair(0, -1)
    };

    /**
     * all the data an ant needs
     */
    private final AntField fields;
    int x;
    int y;
    int steps;


    /**
     * @param fields    the {@code AntField} on which this {@code Ant} operates
     * @param x         x-axis value of the starting position
     * @param y         y-axis value of the starting position
     * @param stepCount initial stepCount of this {@code Ant}.
     * @throws IllegalArgumentException If the {@code Field} at position {@code x,y} does not exist, or
     *                                  if its value is < 0
     */
    public Ant(AntField fields, int x, int y, int stepCount) {
        this.fields = fields;
        this.x = x;
        this.y = y;
        this.steps = stepCount;
        fields.getField(x, y).setValue(stepCount);
    }

    /**
     * This is were the magic happens.
     * If we can move to a field in moore neighbourhood and decrease or initialise the step counter there,
     * then we do and start a new ant thread with it.
     * All neighbour ant threads are submitted to the {@link #pool} and their {@link Future} is saved to a vector.
     * After we checked and started all neighbour ants, we wait for all futures to complete.
     * We do not timeout the Futures as this is done in {@link AntRace}
     * Thus, if this method is invoked by a different class, be careful.
     */
    public void run() {
        // ants from the future. pew pew
        Vector<Future> roboAnts = new Vector<>();

        // and check the neighbours out ;)
        for (Pair moore : moores) {
            int nx = x + moore.x;
            int ny = y + moore.y;
            Field f = fields.getField(nx, ny);
            // fields may be null if negative -> they are walls. We cannot walk on walls. No god ants here
            if (f != null)
                if (f.getValue() == 0 || f.getValue() > steps) {
                    Ant ant = new Ant(fields, nx, ny, steps + 1);
                    // you go little ant! This is your field!
                    roboAnts.add(pool.submit(ant));
                }
        }
        // and wait for our neighbours to wave back at us
        for (Future fu : roboAnts) {
            try {
                if (fu != null)
                    fu.get();
            } catch (InterruptedException | ExecutionException e) {
                // if we are interrupted while waiting, cancel the future we were waiting for.
                e.printStackTrace();
                fu.cancel(true);
            }
        }
    }

    /**
     * A convenience class to represent a simple pair of ints. F*ck you {@code Oracle}
     */
    private static class Pair {
        final int x;
        final int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}