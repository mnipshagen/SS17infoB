package Blatt07.Ex02;

import Blatt07.util.Visitor;
import java.io.File;

/**
 * Created by Mo on 04/06/2017.
 */
public class FileVisitor implements Visitor<File> {
    @Override
    public boolean visit(File o) {
        return false;
    }
}
