package Blatt07.Ex02;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static Blatt07.Ex02.FileVisitorResult.*;


/**
 * A FileVisitor implementation.
 * When visiting a file decided whether to consider it, skip it or stop the whole process
 * via the three options of {@link FileVisitorResult}
 */
public class PathCrawler implements FileVisitor {
    // to we explore?
    private boolean recursive = true;
    // holds all the forbidden files, list for easy access via contains
    private List<String> filter = new ArrayList<>();
    // the root of our visit, default to root of system
    private File root = new File(File.listRoots()[0].getAbsolutePath());

    /**
     * create a new crawler
     * @param root where do we start?
     * @param recursive do we explore?
     */
    public PathCrawler(File root, boolean recursive) {
        this.root = root;
        this.recursive = recursive;
    }

    /**
     * Did not find a relevant implementation so it always returns false
     * @param f file to consider
     * @return false
     */
    @Override
    public boolean visit(File f) {
        return false;
    }

    /**
     * The visit method. Check wheter <code>f</code> should be considered
     * If so, it is printed to the terminal indented according to its depth
     * @param f file to consider
     * @param depth current depth
     * @return {@link FileVisitorResult#SKIP} if file is not to be considered
     *          {@link FileVisitorResult#CONTINUE} if file is to be considered
     *          {@link FileVisitorResult#STOP} if this is the end of all things
     */
    @Override
    public FileVisitorResult visit(File f, int depth) {
        if(f.equals(root))
            return CONTINUE;
        if (skip(f)) {
            return SKIP;
        } else {
            if (f.isFile()) {
                System.out.println(indentFile(depth) + f.getName());
                return CONTINUE;
            } else {
                System.out.println(indentDir(depth) + f.getName());
                return recursive ? CONTINUE : SKIP;
            }
        }

    }

    /**
     * Set a new filter
     * Supports Regex!
     * @param names the list of all names to be skipped
     */
    @Override
    public void applyFilter(String[] names) {
        filter = Arrays.asList(names);
    }

    /**
     * convenience reasons ?
     * @param names supply a list of files which will be reduced to their simple names
     */
    public void applyFilter(File[] names) {
        filter = new ArrayList<>();
        for(File f : names){
            filter.add(f.getName());
        }
    }

    /**
     * @return the current active filter list
     */
    public List<String> getFilter(){
        return filter;
    }

    /**
     * EXPLORATION
     * @param r true if we want to explore
     */
    @Override
    public void setRecursive(boolean r){
        this.recursive = r;
    }

    /**
     * helper function to indent directories accordingly
     * @param depth how far we indent
     * @return a nice printable string
     */
    private static String indentDir(int depth) {
        return new String(new char[depth==0? 0:depth-1]).replace("\0", "│  ") + "├──<Dir> ";
    }

    /**
     * helper function to indent files accordingly
     * @param depth how far we indent
     * @return a nice printable string
     */
    private static String indentFile(int depth){
        return new String(new char[depth-1]).replace("\0","│  ") + "├──<File> ";
    }

    /**
     * Decided whether to skip a file or not
     * @param f file to consider
     * @return true if f should be skipped
     */
    private boolean skip(File f){
        for(String s : filter) {
            if (f.getName().matches(s))
                return true;
        }
        return false;
    }
}
