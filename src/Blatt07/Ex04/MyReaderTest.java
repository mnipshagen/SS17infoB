package Blatt07.Ex04;

import java.io.*;

/**
 * @author Tobias Ludwig (toludwig)
 * @author Mo Nipshagen (mnipshagen)
 */
public class MyReaderTest {
    public static void main(String[] args) {
        String regex = args[0];

        MyReader mr = new MyReader(new InputStreamReader(System.in), regex);

        String line = "";
        int matches = 0;
        while (line != null){
            try {
                line = mr.readLine();
            } catch (IOException e) {
                System.out.println("Exception occurred during reading. Cancel.");
                e.printStackTrace();
                break;
            }

            matches = mr.getAmountOfMatches();
            if(matches > 0) {
                System.out.format("%d in line %d: ", matches, mr.getLineNumber());
                System.out.println("\t" + line);
            }
        }
    }
}
