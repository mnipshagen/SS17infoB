package Blatt07.Ex03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mo on 31/05/2017.
 */
public class FileOfInts {

    /**
     * The directory and file extension we will be using
     */
    private static final String dir = System.getProperty("user.dir") + File.separator + "IntegerArrays" + File.separator;
    private static final String ext = ".array";
    private static final String regex_file = "(\\d+;)*";

    /**
     * ArrayList to represent the data from the file. More powerful than simple array.
     */
    private ArrayList<Integer> a;
    /**
     * keeps the file connection so file cannot be modified from outside while wrapper is active
     */
    private File handle;
    /**
     * position variable to iterate through the array
     */
    private int pos;
    /**
     * holds the path to the file for reopening
     */
    private String path;

    static {
        /*
          create directories if necessary
         */
        if (! (new File(dir).mkdirs())) {
            throw new IOError(new IOException("Could not create necessary directories"));
        }
    }

    /**
     * Eine Instanz dieser Klasse soll mit einem Integer-Array und einem Namen,
     * unter dem das Array als Datei abgespeichert werden soll, instanziiertwerden können.
     * Existiert unter dem Namen bereits eine Datei, soll diese überschrieben werden.
     * Alle Array-Einträge werden dann in die Datei geschrieben.
     * @param array array to safe persistent
     * @param file name of the file to save array in
     */
    public FileOfInts(Integer[] array, String file) throws IOException {
        this.a = new ArrayList<>(Arrays.asList(array));
        this.path = buildFileName(file);
        this.handle = new File(this.path);
        this.pos = 0;

        // debug
//        System.out.println("File created at: " + handle.getAbsolutePath());
            if (handle.exists())
                if (!handle.delete())
                    throw new IOException("Could not delete existing file.");
            if (!handle.createNewFile())
                throw new IOException("Could not create file at: " + this.handle.getAbsolutePath());
            save();
    }

    /**
     * Es soll auch möglich sein, auf ein bereits existierendes, persistentes Array
     * durch Instanziierung der Wrapper-Klasse nur unter Angabe des richtigen Dateinamens Zugriff zu erlangen
     * @param file name of the file array is saved in
     */
    public FileOfInts(String file) throws IOException {
        this.path = buildFileName(file);
        this.handle = new File(this.path);
        this.pos = 0;

        // debug
//        System.out.println("Reading file from: " + handle.getAbsolutePath());
            load();
    }

    /**
     * whether pos is at the end
     * @return true of pos is at last entry
     */
    public boolean endpos() {
        isActive();
        return pos == a.size();
    }

    /**
     * return elem from current position
     * @throws IndexOutOfBoundsException if already at the end
     * @return Integer at pos
     */
    public Integer get() {
        isActive();
        if(endpos())
            throw new IndexOutOfBoundsException("End of Array");
        return get(pos);
    }

    /**
     * like {@link #get()} but with idx instead of pos
     * @param idx position to retrieve
     * @return Integer at idx
     */
    public Integer get(int idx) {
        isActive();
        return a.get(idx);
    }

    /**
     * @return amount of elements saved
     */
    public int count() {
        isActive();
        return a.size();
    }

    /**
     * reset pos to start of array
     */
    public void reset(){
        isActive();
        pos = 0;
    }

    /**
     * advance thorugh the list
     * @throws IndexOutOfBoundsException when already at end
     */
    public void advance() {
        isActive();
        if (endpos())
            throw new IndexOutOfBoundsException("End of Array");
        pos ++;
    }

    /**
     * jump to idx
     * @throws IndexOutOfBoundsException if idx is negative or too high
     * @param idx position ot jump to
     */
    public void goTo(int idx) {
        isActive();
        if (idx < 0 || idx > count())
            throw new IndexOutOfBoundsException("Index out of range");
        pos = idx;
    }

    /**
     * Set integer at current pos to i
     * @param i Integer to insert
     * @return old Integer at this position
     */
    public Integer set(Integer i) {
        return set(pos, i);
    }

    /**
     * set Integer at position idx to i
     * @param idx index to change
     * @param i integer to insert
     * @return null if file was not savable, else old Integer at idx
     */
    public Integer set(int idx, Integer i) {
        isActive();
        Integer old = a.set(idx, i);
        try{
            save();
            return old;
        } catch (IOException e) {
            System.err.println("Could not save file");
            a.set(idx, old);
            return null;
        }
    }

    /**
     * add Integer to end of array
     * Does not add i if file was not savable
     * @param i Integer to add
     */
    public boolean add(Integer i) {
        isActive();
        a.add(i);
        try {
            save();
            return true;
        } catch (IOException e) {
            System.err.println("Could not save file");
            a.remove(i);
            return false;
        }
    }

    /**
     * adds Integer at position idx
     * Does not add i if file was not savable
     * @param idx position to insert at
     * @param i integer to add
     */
    public boolean add(int idx, Integer i) {
        isActive();
        a.add(idx,i);
        try {
            save();
            return true;
        } catch (IOException e) {
            System.err.println("Could not save file");
            a.remove(idx);
            return false;
        }
    }

    /**
     * delete Integer at current pos
     * @return deleted Integer or null if file was not savable
     */
    public Integer delete() {
        return delete(pos);
    }

    /**
     * deletes Integer at position idx
     * @param idx position to delete at
     * @return deleted Integer or null if file was not savable
     */
    public Integer delete(int idx) {
        isActive();
        Integer old = a.remove(idx);;
        try{
            save();
            return old;
        } catch (IOException e) {
            System.err.println("Could not save file");
            a.add(idx,old);
            return null;
        }
    }

    /**
     * Close file handle and render wrapper inactive
     */
    public void close(){
        this.handle = null;
        a = null;
        pos = -1;
    }

    /**
     * attempt to reopen file
     * @return true if file could be opened and loaded, false if not
     */
    public boolean open(){
        try {
            this.handle = new File(this.path);
            pos = 0;
            load();
            return true;
        } catch (IOException e) {
            close();
            return false;
        }
    }

    /**
     * helper function to generate file path
     * @param file name of file
     * @return whole path to file
     */
    private String buildFileName(String file) {
        return dir + file + ext;
    }

    /**
     * attempts to load file into array {@link #a}
     * @throws IOException if file does not exist or is not readable
     * @throws IllegalStateException if file is in wrong format
     */
    private void load() throws IOException {
        if (!handle.exists()) {
            throw new FileNotFoundException("Did not find appropriate file at: " + this.handle.getAbsolutePath());
        }
        FileReader fr = new FileReader(handle);
        char[] buff = new char[(int) handle.length()];
        fr.read(buff);
        fr.close();
        String buffer = String.valueOf(buff);
        if (!buffer.matches(regex_file))
            throw new IllegalStateException("File format wrong");
        String[] values = buffer.split(File.pathSeparator);
        ArrayList<Integer> array = new ArrayList<>();
        for (String s : values) {
            array.add(Integer.valueOf(s));
        }
        a = array;
    }

    /**
     * Attempts to save array to file
     * @throws IOException if file does not exist or is not writable
     */
    private void save() throws IOException {
        if (!handle.exists()) {
            throw new FileNotFoundException("Did not find appropriate file at: " + this.handle.getAbsolutePath());
        }
        FileWriter fw = new FileWriter(handle, false);
        for (Integer i : a) {
            fw.write(String.valueOf(i) + File.pathSeparator);
        }
        fw.close();
    }

    /**
     * helper function to check state of instance
     * @throws IllegalStateException if instance is in "inactive" state
     */
    private void isActive(){
        if (handle == null)
            throw new IllegalStateException("Instance is inactive and handle is closed");
    }

    /**
     * current array
     * @return current array as string
     */
    public String toString(){ return a.toString();}
}
