package Blatt09.Ex01_Mo;

import java.io.File;
import java.util.Timer;

/**
 * A class that when called with a file or directory, attempts to calculate its size every second
 * If the size did in fact change, it will print this to the console
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version 1.0
 */
public class FileObserver {
    public static void main(String[] args) {
        // we need a file to observe. 1. no more no less.
        if (args.length != 1)
            throw new IllegalArgumentException("Supply exactly one path to the file");
        // letting him throw up the IllegalArgumentException,
        // because its reasonable to throw if there is nothing to watch
        final FileObserverTimerTask fott = new FileObserverTimerTask(new File(args[0]));
        // the timer which schedules our fott
        Timer timer = new Timer();
        // thread to call when JVM shuts down to kill fott and make it say its last good byes
        Thread t = new Thread(
                () -> {
                    fott.die();
                    // and remove all that is left in the timer. No leaks please.
                    timer.cancel();
                    timer.purge();
                }
        );
        // t is now a hooked! This tension!
        Runtime.getRuntime().addShutdownHook(t);
        // and here we go! Start with fott and repeat every second
        timer.schedule(fott, 0, 1000);
    }
}
