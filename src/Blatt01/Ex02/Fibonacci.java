package Blatt01.Ex02;

/**
 * Authors: mnipshagen, toludwig
 * <p>
 * A class that can iterate throgh the Fibonacci sequence.
 * Created by Mo on 04.04.2017.
 */
public class Fibonacci
{

    // stores the previous fibonacci number for calculation
    private int previous;
    // stores current fibonacci number
    private int current;

    /**
     * basic constructor. No parameters as Fibonacci always has the same start values
     */
    public Fibonacci()
    {
        this.previous = 0;
        this.current = 1;
    }

    /**
     * calculates and stores the next occurrence of the fibonacci sequence
     *
     * @return the next number of the Fibonacci sequence
     */
    public int next()
    {
        int tmp = this.previous + this.current;
        this.previous = this.current;
        this.current = tmp;

        return this.current;
    }

    public int getCurrent()
    {
        return current;
    }
}
