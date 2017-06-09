package Blatt07.Ex04;

import java.io.EOFException;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Tobias Ludwig (toludwig)
 * @author Mo Nipshagen (mnipshagen)
 *
 * Decorator class for reading linewise and finding a regex in the lines.
 */
public class MyReader extends LineNumberReader {

    private LineNumberReader reader;

    private Pattern pattern;
    private Matcher matcher;
    private int occurrences;

    /**
     * Decorator constructor.
     * @param reader a LineNumberReader
     * @param regex a String for matching the input with
     */
    public MyReader(Reader reader, String regex) {
        super(reader);
        this.reader = new LineNumberReader(reader);
        this.pattern = Pattern.compile(regex);
        occurrences = 0;
    }

    /**
     * Decorator for {@link LineNumberReader#readLine()}.
     * @return the line read, or null if at the end of the file
     * @throws IOException only EOF exceptions are caught, see above
     */
    public String readLine() throws IOException {
        try {
            occurrences = 0;
            String line = reader.readLine();
            if(line == null) // end of stream is reached
                return null;
            line.trim();
            matcher = pattern.matcher(line);
            while(matcher.find()) occurrences++;
            return line;
        } catch (EOFException eof) {
            return null;
        }
    }

    /**
     * Decorator for {@link LineNumberReader#getLineNumber()}
     * @return the line number of the reader
     */
    public int getLineNumber(){
        return reader.getLineNumber();
    }

    /**
     * @return number of matches in the current line
     */
    public int getAmountOfMatches(){
        return occurrences;
    }

}
