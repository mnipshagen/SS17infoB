package Blatt07.Ex02;

import Blatt07.util.Visitable;
import Blatt07.util.Visitor;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Mo on 04/06/2017.
 */
public class FileSys implements Visitable<File>{

    private static File rootdir = File.listRoots()[0];

    private File root;

    public FileSys(String name){
        this.root = new File(rootdir, name);
    }

    public File getRoot(){
        return root;
    }

    public File addDirectory(String name) throws IOException {
        return addDirectory(root,name);
    }

    public File addDirectory(File parent, String name) throws IOException {
        isInRoot(parent);
        if(!root.exists())
            if(!root.mkdir())
                throw new IOException("Could not create root directory");
        File f = new File(parent, name);
        if (!f.mkdir())
            return null;
        else
            return f;
    }

    public File addFile(String name) throws IOException {
        return addFile(root,name);
    }

    public File addFile(File parent, String name) throws IOException {
        isInRoot(parent);
        if(!root.exists())
            if(!root.mkdir())
                throw new IOException("Could not create root directory");
        File f = new File(parent, name);
        if (!f.createNewFile())
            return null;
        else
            return f;
    }

    public File getDirectory(String name){
        return getDirectory(root,name);
    }

    public File getDirectory(File parent, String path){
        isInRoot(parent);
        File dir = new File(parent,path);
        if (!dir.exists() || !dir.isDirectory()){
            throw new IllegalArgumentException("Did not find specified directory");
        } else
            return dir;
    }

    public File getFile(String name){
        return getFile(root,name);
    }

    public File getFile(File parent, String name){
        isInRoot(parent);
        File f = new File(parent,name);
        if (!f.exists() || !f.isFile()){
            throw new IllegalArgumentException("Did not find specified file");
        } else
            return f;
    }

    @Override
    public void accept(Visitor<File> v) {

    }

    private void isInRoot(File f){
        if (!f.getAbsolutePath().contains(root.getAbsolutePath())) {
            throw new IllegalArgumentException("Parent \"" + f + "\" is not in root \"" + root + "\"");
        }
    }
}
