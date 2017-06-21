package Blatt09.Ex01_Mo;

import Blatt09.io.*;

import java.io.File;
import java.util.HashMap;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicLong;

import static Blatt09.io.FileVisitResult.*;

/**
 * Created by Mo on 21/06/2017.
 */
public class FileObserverTimerTask extends TimerTask {

    private final FileSystem sys;
    private final File watching;
    private long size;
    private final Crawler crawler = new Crawler();
    private boolean run = true;
    private final String[] sizeUnits = {"", "Kilo","Mega","Giga","Tera","Peta"};

    public FileObserverTimerTask(File file) throws IllegalArgumentException
    {
        this.sys = new FileSystem(file);
        this.watching = file;
    }
    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        long oldSize = size;
        long time = System.nanoTime();
        size = 0;
        sys.accept(crawler);
        if (oldSize != size)
            print();
//        System.out.printf("TimerTask run took: %.3f ms\n", ((System.nanoTime() - time)/1000000.0));
//        System.out.println("Old size was " + oldSize + " and the new size is " + size);
    }

    private void print()
    {
        String unit = "Bytes";
        double sizePrint = size;
        int thousands = -1;
        while(sizePrint > 1.0)
        {
            sizePrint /= 1024;
            thousands ++;
        }
        sizePrint *= 1024;
        unit = sizeUnits[thousands].concat(unit);

        System.out.println(
            String.format(
                ">> The current size of %s is\n%.3f %s",
                watching, sizePrint, unit
            )
        );
    }

    public void die() {
        System.out.println("Stopping to watch " + watching + ".");
    }

    private class Crawler implements FileVisitor
    {
        @Override
        public FileVisitResult postVisitDirectory(File dir) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(File dir) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFailed(File dir) {
            System.err.println(dir + " will not be considered. Inaccessible.");
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(File file) {
            size += (file.length());
            return CONTINUE;
        }
    }
}


