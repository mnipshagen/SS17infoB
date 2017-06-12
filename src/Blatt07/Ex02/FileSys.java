package Blatt07.Ex02;

import Blatt07.Ex01.Visitable;
import Blatt07.Ex01.Visitor;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import static Blatt07.Ex02.FileVisitorResult.*;

/**
 * This class represents a root of a filesystem and makes it iterable by a {@link FileVisitor}
 * Commented below are also methods for managing the file system
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version 1.0
 */
public class FileSys implements Visitable<File>{

    /**
     * The root of this (sub-)filesystem
     */
    private File root;

    /**
     * Creates a new file system
     * @param path path to the root
     */
    public FileSys(String path){
        if(!new File(path).exists())
            throw new RuntimeException("Root does not exist at " + path);
        this.root = new File(path);
    }

    /**
     * Creates a new file system
     * @param root the root
     */
    public FileSys(File root){
        if(!root.exists())
            throw new RuntimeException("Root does not exist");
        this.root = root;
    }

    /**
     * Creates a new Filesystem with the "first" root of the system as root
     */
    public FileSys(){
        this.root = File.listRoots()[0];
    }

    /**
     * @return the root of this system
     */
    public File getRoot(){
        return root;
    }

    /**
     * Accepts a {@link FileVisitor} to explore the tree of this root
     * @param v the FileVisitor which should be called for every element in this
     * @throws UnsupportedOperationException if <code>v</code> is not a FileVisitor
     */
    @Override
    public void accept(Visitor<File> v) {
        if (v instanceof FileVisitor) {
            FileVisitor v1 = (FileVisitor) v;
            exploreTree(root,v1,0);
        } else
            throw new UnsupportedOperationException("Only supported for class FileVisitor");
    }

    /**
     * The actual traversing of the tree
     * Visits the current file <code>currFile</code>. If it is a directory,
     * the answer of the visitor decides whether to explore the subdirectory
     * @param currFile the file to visit
     * @param v the visitor
     * @param depth the current depth of the tree
     * @return one of {@link FileVisitorResult} for recursive reasons to align with {@link Visitor}
     */
    private FileVisitorResult exploreTree(File currFile, FileVisitor v, int depth) {
        FileVisitorResult check = v.visit(currFile, depth);
        if (check == STOP) return STOP;
        if (currFile.isDirectory()
                && check == CONTINUE){
            File[] fileList = currFile.listFiles();
            Arrays.sort(fileList);
            for (File f : fileList) {
                if( exploreTree(f,v,depth+1) == STOP)
                    return STOP;
            }
        }
        return check;
    }

    /**
     * @return path of root
     */
    public String toString() {
        return root.getAbsolutePath();
    }

    /**
     * adds a directory in the root with name
     * @param name name of the directory
     * @return the created directory
     * @throws IOException if directory could not be created
     */
    public File addDirectory(String name) throws IOException {
        return addDirectory(root,name);
    }

    /**
     * adds a directory in the directory parent with name, if parent is inside of root
     * @param name name of the directory
     * @return the created directory
     * @throws IOException if directory could not be created
     */
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

    /**
     * adds a file in the root with name
     * @param name name of the file
     * @return the created file
     * @throws IOException if file could not be created
     */
    public File addFile(String name) throws IOException {
        return addFile(root,name);
    }

    /**
     * adds a file in the file parent with name, if parent is inside of root
     * @param name name of the file
     * @return the created file
     * @throws IOException if file could not be created
     */
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

    /**
     * attempt to return a directory with name name from root
     * @param name name of the directory
     * @return the directory
     */
    public File getDirectory(String name){
        return getDirectory(root,name);
    }

    /**
     * Attempts to get directory with path from parent
     * @param parent parent file
     * @param path path/name to directory
     * @return directory if found
     * @throws IllegalArgumentException if file does not exist or is no directory
     */
    public File getDirectory(File parent, String path){
        isInRoot(parent);
        File dir = new File(parent,path);
        if (!dir.exists() || !dir.isDirectory()){
            throw new IllegalArgumentException("Did not find specified directory");
        } else
            return dir;
    }

    /**
     * attempt to return a file with name name from root
     * @param name name of the file
     * @return the file
     */
    public File getFile(String name){
        return getFile(root,name);
    }

    /**
     * Attempts to get file with path from parent
     * @param parent parent directory
     * @param name name to file
     * @return file if found
     * @throws IllegalArgumentException if file does not exist or is no file
     */
    public File getFile(File parent, String name){
        isInRoot(parent);
        File f = new File(parent,name);
        if (!f.exists() || !f.isFile()){
            throw new IllegalArgumentException("Did not find specified file");
        } else
            return f;
    }

    /**
     * attempts to remove a file inside root
     * @param name name of the file
     * @return true if successful
     */
    public boolean remove(String name){
        return remove(root,name);
    }

    /**
     * attempts to remove a file inside parent
     * @param parent parent file
     * @param name name of file to delete
     * @return true if successful
     */
    public boolean remove(File parent, String name){
        isInRoot(parent);
        File f = new File(parent,name);
        return f.delete();
    }

    /**
     * checks whether a given file f is inside root or some subdirectory of root
     * @param f file to check
     * @throws IllegalArgumentException if file is not in root
     */
    private void isInRoot(File f){
        if (!f.getAbsolutePath().contains(root.getAbsolutePath())) {
            throw new IllegalArgumentException("Parent \"" + f + "\" is not in root \"" + root + "\"");
        }
    }
}
