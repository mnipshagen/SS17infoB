package Blatt07.Ex02;

import java.io.File;
import java.util.Arrays;

/**
 * A class to mimic the basic usage of the unix <code>ls</code> command
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class List {

    public static void main(String[] args) {
        // use first root as a fall back
        FileSys dir = new FileSys(File.listRoots()[0]);
        // unless specified by arguments no recursive behaviour
        boolean recursive = false;

        // see if we can find some arguments
        // if no known argument was supplied assume file path
        int i = 0;
        for(String s : args) {
            if (Arrays.asList(new String[] {"-r", "--recursive"}).contains(s)) {
                recursive = true;
                i ++;
            }
        }
        // if the only arguments supplied are flags, choose working directory
        if (i == args.length) {
            dir = new FileSys(System.getProperty("user.dir"));
        } else {
            dir = new FileSys(new File(args[i]));
        }

        // and here we go
        System.out.println("Exploring " + dir);
        PathCrawler fv = new PathCrawler(dir.getRoot(),recursive);
        // ignore invisible files
        fv.applyFilter(new String[]{"\\..*"});
        dir.accept(fv);

    }
}
