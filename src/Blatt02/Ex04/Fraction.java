package Blatt02.Ex04;

/**
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

    public static Fraction parseFraction (String frac) {
        if (!frac.matches("\\(?\\s?\\d+\\s?/\\s?\\d+\\s?\\)?")) {
            throw new IllegalArgumentException("The string entered was not a recognised fraction.");
        }
        String[] nums = frac.replace("[(,), ]","").split("/");
        int numerator = Integer.parseInt(nums[0]);
        int denominator = Integer.parseInt(nums[1]);

        return new Fraction(numerator, denominator);
    }

    protected int kgv(int n, int m) {
        return n * m / ggt(n,m);
    }


}
