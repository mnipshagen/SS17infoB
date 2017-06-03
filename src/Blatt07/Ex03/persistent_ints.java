package Blatt07.Ex03;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Mo on 31/05/2017.
 */
public class persistent_ints {

    private static final String dir = "." + File.separator + "IntegerArrays" + File.separator;
    private static final String ext = ".array";

    private ArrayList<Integer> a;
    private String path;
    private int pos;

    public persistent_ints(Integer[] array, String file) {
        this.a = new ArrayList<>(Arrays.asList(array));
        this.path = buildFileName(file);

        File f = new File(this.path);
        // debug
        System.out.println("File created at: " + f.getAbsolutePath());
        try {
            if (f.exists())
                if (!f.delete())
                    throw new IOException("Could not delete existing file.");
            if (!f.createNewFile())
                throw new IOException("Could not create file at: " + this.path);
            FileWriter fw = new FileWriter(f, false);
            for (Integer i : array) {
                fw.write(String.valueOf(i) + File.pathSeparator);
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Could not open file at: " + f.getAbsolutePath());
            e.printStackTrace();
        }

        pos = 0;
    }

    public persistent_ints(String file) {
        this.path = buildFileName(file);
        this.pos = -1;

        File f = new File(this.path);
        // debug
        System.out.println("Reading file from: " + f.getAbsolutePath());
        try {
            if (!f.exists()) {
                throw new FileNotFoundException("Did not find appropriate file at: " + this.path);
            }
            this.a = arrayFromFile();
        } catch (FileNotFoundException e) {
            System.err.println("Could not open file at: " + f.getAbsolutePath());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        pos = 0;
    }

    public boolean empty() {
        return a.size() == 0;
    }

    public boolean endpos() {
        return pos == a.size();
    }

    public Integer elem() {
        return get(pos);
    }

    public Integer get(int idx) {
        return a.get(idx);
    }

    public int count() {
        return a.size();
    }

    public void advance() {
        if (endpos())
            throw new RuntimeException("End of Array");
        pos ++;
    }

    private String buildFileName(String file) {
        return dir + file + ext;
    }

    private ArrayList<Integer> arrayFromFile() throws IOException {
        File f = new File(path);
        FileReader fr = new FileReader(f);
        char[] buff = new char[(int) f.length()];
        int read = fr.read(buff);
        String buffer = String.valueOf(buff);
        String[] values = buffer.split(File.pathSeparator);
        ArrayList<Integer> array = new ArrayList<>();
        for (String s : values) {
            array.add(Integer.valueOf(s));
        }
        return array;
    }
}
