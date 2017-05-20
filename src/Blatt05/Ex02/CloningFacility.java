package Blatt05.Ex02;

import Blatt02.Ex02.StringStack;

/**
 * x.clone() != x                       --> true
 * x.clone().getClass() == x.getClass() --> true
 * x.clone().equals(x)                  --> true
 */
public class CloningFacility {

    public static void main(String[] args) {
        // test data
        List<String> l1 = new List<>();
        l1.add("Hello"); l1.advance();
        l1.add("World"); l1.advance();
        l1.add("Smile"); l1.advance();
        l1.add("for"); l1.advance();
        l1.add("the"); l1.advance();
        l1.add("Camera");
        List<StringStack> l2 = new List<>();
        StringStack x = new StringStack();
        x.push("meeple");
        l2.add(x); l2.advance();
        x.pop(); x.push("steeple");
        l2.add(x); l2.advance();
        x.pop(); x.push("moo");
        l2.add(x); l2.advance();

        // testing the non-absolute requirements
        // once with immutable type string
        // once with mutable type stringstack
        assert l1.clone() != l1 : "l1 == clone";
        assert l1.clone().getClass() == l1.getClass() : "l1 class != clone.class";
        assert l1.clone().equals(l1) : "clone not equals l1";
        assert l2.clone() != l2 : "l2 == clone";
        assert l2.clone().getClass() == l2.getClass() : "l2.class != clone.class";
        assert l2.clone().equals(l2) : "clone not equals l2";

        System.out.println("Done.");
    }
}
