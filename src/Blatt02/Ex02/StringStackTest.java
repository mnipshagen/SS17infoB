package Blatt02.Ex02;

/**
 * Class for testing copy constructor of StringStack.
 *
 * @authors mnipshagen, toludwig
 */
public class StringStackTest{
    public static void main(String[] args) {
        final StringStack original = new StringStack();
        original.push("first");
        original.push("second");
        original.push("third");
        original.push("fourth");

        StringStack copy = new StringStack(original);
        while(!original.empty()){
            System.out.println(String.format("%-20s","Original: " + original.peek()) +
                    String.format("%-15s","Copy:" + copy.peek()));
            if(original.peek() == copy.peek()) {
                System.out.println("If you see this, copy was shallow...");
            }
            if(!original.pop().equals(copy.pop())) {
                System.out.println("Not equal, bad copy!");
                break;
            }
        }
        if(!copy.empty()) {
            System.out.println("Copy is not empty, bad copy!");
        }

        System.out.println("Done.");
    }
}
