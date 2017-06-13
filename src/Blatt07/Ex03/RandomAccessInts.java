package Blatt07.Ex03;

import java.io.*;
import java.util.Arrays;

/**
 * Created by nipsh on 13/06/2017.
 */
public class RandomAccessInts implements AutoCloseable
{
    /**
     * The directory and file extension we will be using and the byte size of int
     */
    private static final String dir = System.getProperty("user.dir") + File.separator + "IntegerArrays" + File.separator;
    private static final String ext = ".ser";
    private static final String mode = "rws";
    private static final int byteSize = 4;
    /**
     * keeps the file connection so file cannot be modified from outside while wrapper is active
     */
    private RandomAccessFile handle;
    /**
     * holds the path to the file for reopening
     */
    private String path;

    static
    {
        /*
          create directories if necessary
         */
        if (! (new File(dir).mkdirs()))
        {
            throw new IOError(new IOException("Could not create necessary directories"));
        }
    }

    public RandomAccessInts(Integer[] array, String name) throws IOException {
        this.path = buildFileName(name);
            File f = new File(path);
            if (f.exists())
                if(!f.delete())
                    throw new IOException("Could not delete File. Do we have Permission?");

            this.handle = new RandomAccessFile(f, mode);
            for (Integer i :
                    array) {
                this.handle.writeInt(i);
            }
    }

    public RandomAccessInts(String name) throws FileNotFoundException {
        this.path = buildFileName(name);
        File f = new File(path);
            if (!f.exists())
                throw new FileNotFoundException("Did not find file at " + path);
            this.handle = new RandomAccessFile(f, mode);
    }

    public int count() throws IOException {
        return Math.toIntExact(handle.length() / byteSize);
    }

    public Integer get(int idx)
    {
        try {
            this.handle.seek(idx * byteSize);
            Integer res = this.handle.readInt();
            this.handle.seek(0);
            return res;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean set(int idx, Integer o) throws IOException {
            this.handle.seek(idx * byteSize);
            this.handle.writeInt(o);
            return true;

    }

    public boolean delete(int idx, Integer o)
    {
        try {
            this.handle.seek((idx+1) * byteSize);
            byte[] tmp = new byte[(int) (this.handle.length() - (idx+1) * byteSize)];
            this.handle.readFully(tmp);
            this.handle.seek(idx * byteSize);
            this.handle.write(tmp);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() throws IOException {
        this.handle.close();
    }

    /**
     * helper function to generate file path
     * @param file name of file
     * @return whole path to file
     */
    private String buildFileName(String file) {
        return dir + file + ext;
    }

}
