package Blatt07.Ex03;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Class to test all methods and most Exceptions of {@link FileOfInts}
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class persistency_test_for_ints
{

    public static void main(String[] args)
    {
        Integer[] a, b;
        a = new Integer[10];
        for (int i = 0; i < 10; i++)
        {
            a[i] = i;
        }
        FileOfInts fa;
        try
        {
            new FileOfInts(a, "file_a");
            fa = new FileOfInts("file_a");

            if (fa.count() != a.length)
                System.out.println("Length incorrect.");

            for (int i = 0; !fa.endpos(); i++)
            {
                if (fa.get() != a[i])
                    System.out.println("Element at position " + i + " is incorrect");
                fa.advance();
            }
            fa.reset();
            fa.close();

            try
            {
                fa.get();
                System.out.println("Able to get element " + fa.get() + " when inactive");
            }
            catch (IllegalStateException ignored)
            {
            }

            fa.open();
            try
            {
                fa.get(fa.count() + 1);
                System.out.println("Able to \"get\" out of bounds.");
            }
            catch (IndexOutOfBoundsException ignored)
            {
            }

            fa.set(0, 12);
            a[0] = 12;
            if (fa.get(0) != 12)
                System.out.println("After set, get is wrong");

            fa.advance();
            int c = fa.count();
            fa.delete();
            if (c != fa.count() + 1)
                System.out.println("Size is not 1 smaller after delete");

            fa.add(1, 1);

            fa.goTo(8);
            if (a[8] != fa.get())
                System.out.println("goTo false index.\n" + fa.toString() + "\n" + Arrays.toString(a));

            fa.close();


            File f = new File(System.getProperty("user.dir") + File.separator + "IntegerArrays" + File.separator);
            String[] entries = f.list();
            for (String s : entries)
            {
                File currentFile = new File(f.getPath(), s);
                currentFile.delete();
            }
            f.delete();
            if (fa.open())
                System.out.println("Could open non existing file");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        System.out.println("Done.");
    }
}
