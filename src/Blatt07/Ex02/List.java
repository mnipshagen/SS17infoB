package Blatt07.Ex02;

import java.io.File;

/**
 * Created by nipsh on 05/06/2017.
 */
public class List {

    public static void main(String[] args) {
        try {
            FileSys fs = new FileSys("meeple");
            fs.addDirectory("dir");
            File dir = fs.getDirectory("dir");
            fs.addFile(dir, "name");
        } catch (Exception e) {
            System.out.println("Fuck me");
        }
    }
}
