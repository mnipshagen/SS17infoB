package Blatt08.Ex04;

import Blatt08.io.FileSystem;
import Blatt08.io.FileVisitResult;
import Blatt08.io.FileVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Blatt08.io.FileVisitResult.*;

/**
 * This class implements a file crawler to explore each file in a directory or single file and search each file
 * for a regular expression and replaces all occurrences of it with the given String. Recursive behaviour for
 * subtree exploration might be set via <code>-r</code> or <code>--recursive</code> arguments.
 *
 * Usage: <code>java Blatt08.Ex04.Replace [-r] regex_search replace [Path]</code>
 *
 * If no path is supplied the working directory is searched for. Per default, invisible files (starting with ".")
 * and several file formats as well as the "src" folder are ignored.
 */
public class Replace
{
    // represents the "new line" char for the system
    private static final String NL = System.getProperty("line.separator");
    // whey debug mode
    private static boolean debug = false;

    public static void main(String[] args)
    {
        try
        {
            // unless specified by arguments no recursive behaviour
            boolean recursive = false;
            FileSystem dir;

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
                if (Arrays.asList(new String[]{"-D", "--debug"}).contains(s))
                {
                    debug = true;
                    i++;
                }
            }
            // check if there are two more arguments for search and replace
            if (args.length - i < 2)
            {
                throw new IllegalArgumentException("Supply search and replace String.");
            }
            // is there a file path specified?
            if (args.length - i - 2 > 0)
            {
                dir = new FileSystem(new File(args[i + 2]));
            }
            else
            {
                dir = new FileSystem(new File(System.getProperty("user.dir")));
            }
            // creating the visitor with all set options and adding a blacklist
            Visitor v = new Visitor(dir.getRootPath(), recursive, args[i], args[i + 1]);
            v.addFilter("\\..*",".*\\.(java|class|pdf|html|js|css|iml|png|jpg|jpeg|jar)",".*src.*",".*out.*");

            if (debug) System.out.println("Crawling through " + dir.getRootPath() + " and recursive: " + recursive);
            // here we go
            dir.accept(v);
        }
        catch (Exception e)
        {
            manual(e);
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
        System.out.println("|        [-r] Search Replacement [FileOrDirectory]         |");
        System.out.println("|                                                          |");
        System.out.println("|             The error stacktrace is below                |");
        System.out.println("+----------------------------------------------------------+");
        e.printStackTrace();
    }

    /**
     * An inner class that does the visit work on our FileSystem
     */
    private static class Visitor
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
        // the string to insert
        private String replace;

        Visitor(File root, boolean r, String search, String replace)
        {
            this.root = root;
            recursive = r;
            this.search = search;
            this.replace = replace;
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
            return (dir.equals(root) || recursive) && !skip(dir) ? CONTINUE : SKIP_SUBTREE;
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
         * For each visited file, check whether it is on the blacklist. If not then stream the whole file line by line
         * connected by "new line" characters into a single string.
         * Replace all occurences of {@link #search} with {@link #replace} in that string.
         * Finally flush to file, replacing the old one.
         *
         * @param file the file that is visited
         * @return if the visit should continue or not.
         */
        @Override
        public FileVisitResult visitFile(File file)
        {
            if (debug) System.out.println("Visiting " + file);
            // check blacklist
            if (!skip(file))
            {
                String content = "";
                // try with resources (love that one) to read aaaaaaall the contents
                try (BufferedReader in = new BufferedReader(new FileReader(file)))
                {
                    String line = in.readLine();
                    while (line != null)
                    {
                        content = content.concat(NL).concat(line);
                        line = in.readLine();
                    }
                    if (debug) System.out.println(String.format("Read content:\n%.50s", content));
                    // replace all the things
                    content = content.replaceAll(search, replace);
                    if (debug) System.out.println(String.format("\nReplaced:\n%.50s", content));
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return TERMINATE;
                }
                // try with resources to write to the file. no append to overwrite contents
                try (FileWriter fw = new FileWriter(file, false))
                {
                    fw.write(content);
                    if (debug) System.out.println("Flushed to file.");
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    return TERMINATE;
                }
                    if (debug) System.out.println("Moving on.");
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
