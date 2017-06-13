package Blatt07.Ex04;

import java.io.*;
import java.util.regex.PatternSyntaxException;

/**
 * @author Tobias Ludwig (toludwig)
 * @author Mo Nipshagen (mnipshagen)
 */
public class MyReaderTest {
    public static void main(String[] args){
        if(args.length == 0){
            throw new IllegalArgumentException("Should supply a regex as argument.");
        }
        String regex = args[0];

        MyReader mr = null;
        try {
            mr = new MyReader(new InputStreamReader(System.in), regex);
        } catch(PatternSyntaxException e){
            System.out.println("Your regex was malformed.");
            e.printStackTrace();
        }

        String line = "";
        int matches = 0;
        while (line != null){
            try {
                line = mr.readLine();
            } catch (IOException e) {
                System.out.println("Exception occurred during reading. Cancel.");
                e.printStackTrace();
                break;
            } finally {
                try {
                    mr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            matches = mr.getAmountOfMatches();
            if(matches > 0) {
                System.out.format("%d in line %d: ", matches, mr.getLineNumber());
                System.out.println("\t" + line);
            }
        }
    }
}
