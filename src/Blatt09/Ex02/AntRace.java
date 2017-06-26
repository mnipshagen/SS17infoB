package Blatt09.Ex02;

/**
 * A test class for the multithreaded ant racing
 * Tests all (at the time of implementation) fields of {@link AntFields}
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version 1.0
 */
public class AntRace implements AntFields {

    public static void main(String[] args) {
        // we are testing all the fields!
        AntField[] fields = {
                new AntField(FIELD1),
                new AntField(FIELD2),
                new AntField(FIELD3),
                new AntField(FIELD4),
                new AntField(FIELD5),
                new AntField(FIELD6)
        };
        // start positions for each field
        int[][] startPos = {
                {2, 4},
                {1, 2},
                {0, 0},
                {2, 4},
                {0, 1},
                {0, 0}
        };
        // for each field ...
        for (int i = 0; i < fields.length; i++) {
            // ... set the starting point ...
            AntField field = fields[i];
            int x = startPos[i][0];
            int y = startPos[i][1];
            // ... give a nice heads up ...
            System.out.println("Starting to fill field " + (i + 1) + " ...");
            // (time meausres)
            long time = System.nanoTime();
            // ... ready, ...
            Ant ant = new Ant(field, x, y, 1);
            // ... steady, ...
            Thread t = new Thread(ant);
            // ... GO! ...
            t.start();
            try {
                // ... wait a max of 10 seconds before cancelling the thread ...
                t.join(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                // ... and tell us if we did ...
                System.err.println("Interrupted ant racing for field " + i);
            }
            // ... give us all the data ...
            System.out.printf("... done. The ant racing took %.3f milliseconds.\n\n", ((System.nanoTime() - time) / 1000000.0));
            // ... and the result
            System.out.println(field.toString());
        }
        System.out.println("\nAll done. :)");
        // shutdown thread pool
        Ant.pool.shutdown();
    }
}
