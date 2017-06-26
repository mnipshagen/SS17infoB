package Blatt09.Ex01;

import Blatt09.io.FileSystem;
import Blatt09.io.FileVisitResult;
import Blatt09.io.FileVisitor;

import java.io.File;
import java.util.TimerTask;

import static Blatt09.io.FileVisitResult.CONTINUE;

/**
 * This Timer Task takes a file to watch over.
 * Everytime it is run, it walks the whole tree if the given file was a directory.
 * If the size changed, then we print this to the terminal.
 * <p>
 * This approach was chosen since:
 * * Directories have no overall size attribute that would be callable directly
 * * The "last modified" timestamp gets updated in such a fucked up way that it was near
 * impossible to implement all possibilities for all file systems
 * <p>
 * I am aware that this is immensly ineffective for larger file trees, but that is in the nature of this task
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version 1.1
 */
public class FileObserverTimerTask extends TimerTask {

    // the filesystem to crawl
    private final FileSystem sys;
    // the root file we are watching (saved for printing reasons)
    private final File watching;
    // this is our visitor
    private final Crawler crawler = new Crawler();
    // unit conversion for readability
    private final String[] sizeUnits = {"", "Kilo", "Mega", "Giga", "Tera", "Peta"};
    // this is where we save the byte size
    private long size;

    public FileObserverTimerTask(File file) throws IllegalArgumentException {
        this.sys = new FileSystem(file);
        this.watching = file;
        this.size = 0;
    }

    /**
     * Save oldsize, reset size to 0, start crawling
     * If size changed -> print
     */
    @Override
    public void run() {
        long oldSize = size;
//        long time = System.nanoTime();
        size = 0;
        sys.accept(crawler);
        print(oldSize != size);
//        System.out.printf("TimerTask run took: %.3f ms\n", ((System.nanoTime() - time)/1000000.0));
//        System.out.println("Old size was " + oldSize + " and the new size is " + size);
    }

    /**
     * Convenience method for formatting output when size changed
     */
    private void print(boolean b) {
        // converting size to a reasonable unit (1 MB really looks ugly, when expressed in byte size)
        String unit = "Bytes";
        double sizePrint = size;
        int thousands = -1;
        while (sizePrint > 1.0) {
            sizePrint /= 1024;
            thousands++;
        }
        sizePrint *= 1024;
        unit = sizeUnits[thousands].concat(unit);

        // printy print
        System.out.printf(
                "\r>> The current size of %s is " + (b ? "(!) " : "") + "%.3f %-15s",
                watching, sizePrint, unit
        );
    }

    /**
     * to be called when thread is shut down
     */
    public void die() {
        System.out.println("\nStopping to watch " + watching + ".");
    }

    /**
     * The visitor to crawl the files and add up all their sizes
     * Whatever happens we continue on through the tree.
     */
    private class Crawler implements FileVisitor {
        @Override
        public FileVisitResult postVisitDirectory(File dir) {
            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(File dir) {
            size += dir.length();
            return CONTINUE;
        }

        @Override
        public FileVisitResult visitFailed(File dir) {
            System.err.println(dir + " will not be considered. Inaccessible.");
            return CONTINUE;
        }

        /**
         * encountering a file: add its size
         *
         * @param file the file that is visited
         * @return CONTINUE, no matter what
         */
        @Override
        public FileVisitResult visitFile(File file) {
            size += (file.length());
            return CONTINUE;
        }
    }
}


