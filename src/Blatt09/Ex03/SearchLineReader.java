package Blatt09.Ex03;

import Blatt09.io.FileSystem;
import Blatt09.io.FileVisitResult;
import Blatt09.io.FileVisitor;
import Blatt09.io.MyReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.concurrent.*;
import java.util.regex.PatternSyntaxException;

import static Blatt09.io.FileVisitResult.CONTINUE;

/**
 * @author Tobias Ludwig (toludwig)
 * @author Mo Nipshagen (mnipshagen)
 *
 * A command line program for searching text files in a file system
 * for a given regex.
 *
 * Usage: <code>java Blatt09.Ex03.SearchLineReader [-r] regex [path]</code>
 *
 * If no path is supplied the working directory is searched for. Per default, invisible files (starting with ".")
 * and several file formats as well as the "src" folder are ignored.
 */
public class SearchLineReader {

    private static Vector<Future> threads = new Vector<>();

    /**
     * For storing the regex matches of each visited File.
     * Because different threads are searching at the same time, this needs to be thread-safe.
     */
    private ConcurrentHashMap<File, String> matchesOfFile = new ConcurrentHashMap<>();

    /**
     * For thread-execution, use cached thread pool.
     */
    private ExecutorService executor = Executors.newCachedThreadPool();

    private Visitor v;
    private FileSystem fs;
    private boolean recursive;
    private String regex;

    /**
     * Create SearchLineReader for a given FileSystem.
     * @param fs a {@link FileSystem}
     */
    public SearchLineReader(FileSystem fs, String regex, boolean recursive) {
        this.fs = fs;
        this.regex = regex;
        this.recursive = recursive;
    }

    public static void main(String[] args) {

        // use first root as a fall back
        FileSystem dir = new FileSystem(File.listRoots()[0]);
        // unless specified by arguments no recursive behaviour
        boolean recursive = false;
        String regex = null;

        // see if we can find some arguments
        // if no known argument was supplied assume file path
        int i = 0;
        for (String s : args)
        {
            if (Arrays.asList(new String[]{"-r", "--recursive"}).contains(s))
            {
                recursive = true;
                i++;
            }
        }

        try{
            regex = args[i];
            i++;
        } catch (ArrayIndexOutOfBoundsException e){
            manual(e);
            return;
        }

        // if there is an arg left, this is the path
        if (i < args.length)
        {
            dir = new FileSystem(new File(args[i]));
        }
        else
        {
            dir = new FileSystem(new File(System.getProperty("user.dir")));
        }

        SearchLineReader s = new SearchLineReader(dir, regex, recursive);
        s.startSearch();

        for (Future f : threads)
            try {
                f.get(10, TimeUnit.SECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                for (Future failed : threads)
                    failed.cancel(true);
                e.printStackTrace();
            }

        try {
            s.printResult();
        } catch (TimeoutException | InterruptedException e){
            System.out.println("Search either timed out or was interrupted.");
        }
    }

    /**
     * print a manual?
     * @param e Exception that was caught before
     */
    private static void manual(Exception e)
    {
        System.out.println("+----------------------------------------------------------+");
        System.out.println("|            Your command seems to be faulty               |");
        System.out.println("|           Formulate your command like this:              |");
        System.out.println("|             [-r] Search [FileOrDirectory]                |");
        System.out.println("|                                                          |");
        System.out.println("|             The error stacktrace is below                |");
        System.out.println("+----------------------------------------------------------+");
        e.printStackTrace();
    }


    private void startSearch(){

        // creating the visitor with all set options and adding a blacklist
        v = new Visitor(fs.getRootPath(), recursive);
        v.addFilter("\\..*",".*\\.(class|pdf|png|jpg|jpeg|jar)");
        // accept the visitor
        fs.accept(v);
    }

    /**
     * Print the output of all threads.
     */
    private void printResult() throws InterruptedException, TimeoutException {
        for(ConcurrentHashMap.Entry<File, String> entry : matchesOfFile.entrySet()) {
            File file = entry.getKey();
            String summary = entry.getValue();

            if (!summary.equals("")) { // if there are matches...
                System.out.println("\nFound matches in file " + file.getAbsolutePath());
                System.out.println(summary); // show them
            }
        }
    }

    /**
     * Checks the file for matches. Returns a summary of the matches in a linewise fashion.
     * @param file the file to be checked
     * @return a summary in the format given by {@link MyReader#summarizeMatches()}
     */
    private String checkFile(File file){

        MyReader mr = null;
        String summary = "";
        try
        {
            // read the file contents and get all matches of the regex
            mr = new MyReader(new FileReader(file), regex);
            summary = mr.summarizeMatches();
        }
        catch (PatternSyntaxException e)
        {
            System.out.println("Your regex was malformed.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error while reading file: " + file.getAbsolutePath() + ". CONTINUE...");
        }

        return summary;
    }


    /**
     * An inner class that does the visit work on our FileSystem
     */
    private class Visitor
            implements FileVisitor
    {
        // do we explore?
        private boolean recursive = false;
        // holds all the forbidden files, list for easy access via contains
        private java.util.List<String> filter = new ArrayList<String>();
        // the root of our visit, default to root of system
        private File root = new File(File.listRoots()[0].getAbsolutePath());
        // the regex to search for
        private String search;

        /**
         * Constructor for the Visitor.
         * @param root default: root of System
         * @param r recursive? default = false
         */
        Visitor(File root, boolean r)
        {
            this.root = root;
            recursive = r;
        }

        public void addFilter(String... a)
        {
            filter.addAll(Arrays.asList(a));
        }

        /**
         * Called by the visited FileSystem after a directory has been read, i.e.
         * after every file and sub-directory in <code>dir</code> has been visited by
         * this FileVisitor. May return {@link FileVisitResult#CONTINUE} if the
         * visiting of files and directories should continue or
         * {@link FileVisitResult#TERMINATE} if not.
         * {@link FileVisitResult#SKIP_SUBTREE} will have the same effect as
         * {@link FileVisitResult#CONTINUE}.
         *
         * @param dir the directory that has been visited
         * @return if the visit should continue or not.
         */
        @Override
        public FileVisitResult postVisitDirectory(File dir)
        {
            return CONTINUE;
        }

        /**
         * Called by the visiting FileSystem before a directory will recursively be
         * read. May return {@link FileVisitResult#CONTINUE} if the directory should
         * be read or {@link FileVisitResult#SKIP_SUBTREE} if the given directory
         * should not be read but the visit of the FileSystem should continue in
         * general. Should return {@link FileVisitResult#TERMINATE} if the visit of
         * the FileSystem in general should end.
         *
         * @param dir the directory that may be visited
         * @return if the visit should continue, skip the given directory or end at
         * all.
         */
        @Override
        public FileVisitResult preVisitDirectory(File dir)
        {
            return (dir.equals(root) || recursive) && !skip(dir) ? CONTINUE : FileVisitResult.SKIP_SUBTREE;
        }

        /**
         * Called by the visiting FileSystem if the visitation of a directory failed,
         * thus if the directory is not-readable. May return
         * {@link FileVisitResult#CONTINUE} if the visiting of files and directories
         * should continue or {@link FileVisitResult#TERMINATE} if not.
         * {@link FileVisitResult#SKIP_SUBTREE} will have the same effect as
         * {@link FileVisitResult#CONTINUE}.
         *
         * @param dir the directory that could not be visited
         * @return if the visit should continue or not.
         */
        @Override
        public FileVisitResult visitFailed(File dir)
        {
            return CONTINUE;
        }

        /**
         * For each visited file, first check whether it is on the blacklist.
         * If not, call {@link MyReader#summarizeMatches()} on the file.
         * If matches are found, print them.
         *
         * @param file the file that is visited
         * @return if the visit should continue or not.
         */
        @Override
        public FileVisitResult visitFile(File file)
        {
            if (!skip(file))
            {
                threads.add(executor.submit((Runnable) () -> checkFile(file)));
            }
            // and forth we go
            return CONTINUE;

        }

        /**
         * Decided whether to skip a file or not
         *
         * @param f file to consider
         * @return true if f should be skipped
         */
        private boolean skip(File f)
        {
            for (String s : filter)
            {
                if (f.getName().matches(s))
                    return true;
            }
            return false;
        }
    }
}
