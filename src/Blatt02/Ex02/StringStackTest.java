package Blatt02.Ex02;

/**
 * Class for testing copy constructor of StringStack.
 *
 * @authors mnipshagen, toludwig
 */
public class StringStackTest{
    public static void main(String[] args) {
        StringStack s1 = new StringStack();
        s1.push("first");
        s1.push("second");
        s1.push("third");
        s1.push("fourth");

        StringStack s2 = new StringStack(s1);
        while(!s1.empty()){
            System.out.println("S1: " + s1.peek() + "\t S2:" + s2.peek());
            if(s1.peek() == s2.peek()) {
                System.out.println("If you see this, copy was shallow...");
            }
            if(!s1.pop().equals(s2.pop())) {
                System.out.println("Not equal, bad copy!");
                break;
            }
        }

        System.out.println("Done.");
    }
}
