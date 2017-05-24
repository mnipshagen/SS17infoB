package Blatt06.Ex02;

/**
 * A class to test the new version of {@link Fraction}.
 * Each fraction, that is fully cancelled the same, will always return the same Fraction by reference
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class FractionTest {

    public static void main(String[] args) {
        Fraction g1, g2, g3, h1, h2;
        g1 = Fraction.getFraction(3, 7);
        g2 = Fraction.parseFraction("9/21");
        g3 = Fraction.getFraction(-6, -14);

        h1 = Fraction.parseFraction("- 13 / 21");
        h2 = Fraction.getFraction(39, -63);

        assert g1 == g2 : "g1 & g2 have differing references";
        assert g1 == g3 : "g1 & g3 have differing references";
        assert h1 == h2 : "h1 & h2 have differing references";
        assert g1 != h1 : "g1 & h1 have the same reference";

        System.out.println("Done.");
    }
}
