package Blatt02.Ex04;

/**
 * An extension of the earlier programmed Fraction class
 * This adds the possibility to add or subtract fractions
 * and also to parse a fraction from a string representation
 * to an actual fraction instance
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Fraction extends Blatt01.Ex03.Fraction {
    /**
     * Konstruktor fuer ganze zahlen, weil Integer nicht ins Schema passt
     *
     * @param n Der Zaehler, der Nenner ist bei Ganzzahlen 1
     */
    public Fraction(int n) {
        super(n);
    }

    /**
     * Konstruktor der den Bruch aus 2 uebergebenen Integers kreirt
     *
     * @param n Zaehler
     * @param d Nenner
     */
    public Fraction(int n, int d) {
        super(n, d);
    }

    /**
     * Adds another fraction to this fraction
     * @param addend the fraction to add
     * @return the sum
     */
    public Fraction add (Fraction addend) {
        if (this.getDenominator() == addend.getDenominator()) {
            int new_n = this.getNumerator() + addend.getNumerator();
            return new Fraction(new_n, this.getDenominator());
        }
        int kgv = kgv(this.getDenominator(), addend.getDenominator());
        int new_n = this.getNumerator() * kgv / this.getDenominator()
                + addend.getNumerator() * kgv / addend.getDenominator();
        return new Fraction(new_n,  kgv);
    }

    /**
     * subtracts another fraction from this fraction
     * @param subtrahend the fraction to subtract
     * @return the difference
     */
    public Fraction subtract(Fraction subtrahend) {
        if (this.getDenominator() == subtrahend.getDenominator()) {
            int new_n = this.getNumerator() - subtrahend.getNumerator();
            return new Fraction(new_n, this.getDenominator());
        }
        int kgv = kgv(this.getDenominator(), subtrahend.getDenominator());
        int new_n = this.getNumerator() * kgv / this.getDenominator()
                - subtrahend.getNumerator() * kgv / subtrahend.getDenominator();
        return new Fraction(new_n,  kgv);
    }

    /**
     * Tries to create a Fraction from a given string representation
     * It checks whether the fraction matches a given regular expression
     * to allow for some variability in the input
     * @param frac string representation of the fraction
     * @return the fraction created from {@param frac}
     */
    public static Fraction parseFraction (String frac) {
        if (!frac.matches("\\(?\\s?-?\\s?\\d+\\s?/\\s?\\d+\\s?\\)?")) {
            throw new IllegalArgumentException("The string entered was not a recognised fraction.");
        }
        String[] nums = frac.replaceAll("[()\\s]","").split("/");
        int numerator = Integer.parseInt(nums[0]);
        int denominator = Integer.parseInt(nums[1]);

        return new Fraction(numerator, denominator);
    }

    /**
     * since we need to equalise the denominators of the fractions to
     * subtract or add them we need to find the least common multiplicative
     * @param n first number
     * @param m second number
     * @return the lcm of the two given numbers
     */
    protected int kgv(int n, int m) {
        return n * m / ggt(n,m);
    }


}
