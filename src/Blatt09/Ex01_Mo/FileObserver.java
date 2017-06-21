package Blatt09.Ex01_Mo;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Mo on 21/06/2017.
 */
public class FileObserver
{
    public static void main(String[] args) {
        if(args.length != 1)
            throw new IllegalArgumentException("Supply exactly one path to the file");
        Timer timer = new Timer();
        // letting him throw up the IllegalArgumentException,
        // because its reasonable to throw if there is nothing to watch
        final FileObserverTimerTask fott = new FileObserverTimerTask(new File(args[0]));
        Thread t = new Thread(
                () -> {
                    fott.die();
                    timer.cancel();
                    timer.purge();
                }
        );
        Runtime.getRuntime().addShutdownHook(t);
        timer.schedule(fott, 0, 1000);
    }
}
