package Blatt08.Ex04;

import Blatt08.io.FileSystem;
import Blatt08.io.FileVisitResult;
import Blatt08.io.FileVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static Blatt08.io.FileVisitResult.*;

/**
 * Created by nipsh on 15/06/2017.
 */
public class Replace
{
    private static final String NL = System.getProperty("line.separator");

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
            }
            if (args.length - i < 2)
            {
                throw new IllegalArgumentException("Supply search and replace String.");
            }

            if (args.length - i - 1 > 0)
            {
                dir = new FileSystem(new File(args[i + 2]));
            }
            else
            {
                dir = new FileSystem(new File(System.getProperty("user.dir")));
            }

            Visitor v = new Visitor(dir.getRootPath(), recursive, args[i], args[i + 1]);
            v.addFilter("\\..*");

            dir.accept(v);
        }
        catch (Exception e)
        {
            manual(e);
        }
    }

    private static void manual(Exception e)
    {
        e.printStackTrace();
    }

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
            return (!dir.equals(root)) && (skip(dir) || recursive) ? SKIP_SUBTREE : CONTINUE;
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
         * Called by the visiting FileSystem to visit a file with this FileVisitor.
         * May return {@link FileVisitResult#CONTINUE} if the visiting of files and
         * directories should continue or {@link FileVisitResult#TERMINATE} if not.
         * {@link FileVisitResult#SKIP_SUBTREE} will have the same effect as
         * {@link FileVisitResult#CONTINUE}.
         *
         * @param file the file that is visited
         * @return if the visit should continue or not.
         */
        @Override
        public FileVisitResult visitFile(File file)
        {
            String content = "";

            try (BufferedReader in = new BufferedReader(new FileReader(file)))
            {
                String line = in.readLine();
                while (line != null)
                {
                    content = content.concat(NL).concat(line);
                    line = in.readLine();
                }
                content = content.replaceAll(search, replace);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return TERMINATE;
            }
            try (FileWriter fw = new FileWriter(file, false))
            {
                fw.write(content);
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return TERMINATE;
            }
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
