package Blatt04.Ex01;

/**
 * An extension of the earlier programmed Fraction class
 * This adds the possibility to add or subtract fractions
 * and also to parse a fraction from a string representation
 * to an actual fraction instance
 *
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Fraction
        extends Number
{

    // Zaehler des Bruchs
    private int numerator;
    // Nenner des Bruchs
    private int denominator;

    /**
     * Konstruktor fuer ganze zahlen, weil Integer nicht ins Schema passt
     *
     * @param n Der Zaehler, der Nenner ist bei Ganzzahlen 1
     */
    public Fraction(int n)
    {
        this(n, 1);
    }

    /**
     * Konstruktor der den Bruch aus 2 uebergebenen Integers kreirt
     *
     * @param n Zaehler
     * @param d Nenner
     */
    public Fraction(int n, int d)
    {
        if (d == 0)
        {
            throw new IllegalArgumentException("Dividing by 0. Really?");
        }
        if (d < 0)
        {
            d *= -1;
            n *= -1;
        }
        int ggt = ggt(n, d);
        this.numerator = n / ggt;
        this.denominator = d / ggt;
    }

    /**
     * gibt den Zaehler aus, damit die variable private bleibt
     *
     * @return Numerator
     */
    public int getNumerator()
    {
        return this.numerator;
    }

    /**
     * Gibt den Nenner aus, damit die Variable private bleibt
     *
     * @return denominator
     */
    public int getDenominator()
    {
        return this.denominator;
    }

    /**
     * Multipliziert den Bruch mit einer ganzen Zahl. whey.
     *
     * @param factor der Multiplikator.
     * @return den multiplizierten Bruch
     */
    public Fraction multiply(int factor)
    {
        int newnum = this.numerator * factor;

        return new Fraction(newnum, this.getDenominator());
    }

    /**
     * Multiplieziert mit einem anderen Bruch. n*n und d*d
     *
     * @param factor der multiplikator Bruch
     * @return Das Produkt
     */
    public Fraction multiply(Fraction factor)
    {
        int newnum = this.numerator * factor.getNumerator();
        int newdenom = this.denominator * factor.getDenominator();

        return new Fraction(newnum, newdenom);
    }

    /**
     * Multipliziert den Bruch mit vielen, vielen Bruechen
     *
     * @param factors Liste mit Faktoren
     * @return Das (Bruttosozial)Produkt
     */
    public Fraction multiply(Fraction... factors)
    {
        Fraction f =
                new Fraction(this.getNumerator(), this.getDenominator());

        for (Fraction fs : factors)
        {
            f = f.multiply(fs);
        }

        return f;
    }

    /**
     * Durch Brueche teilen ist mit dem Kehrbruch mal nehmen. whey.
     *
     * @param divisor Der zu drehende Bruch
     * @return Das Produkt der entstandenen Multiplikation
     */
    public Fraction divide(Fraction divisor)
    {
        Fraction f =
                new Fraction(divisor.getDenominator(),
                             divisor.getNumerator());

        return this.multiply(f);
    }

    /**
     * Adds another fraction to this fraction
     *
     * @param addend the fraction to add
     * @return the sum
     */
    public Fraction add(Fraction addend)
    {
        if (this.getDenominator() == addend.getDenominator())
        {
            int new_n = this.getNumerator() + addend.getNumerator();
            return new Fraction(new_n, this.getDenominator());
        }
        int kgv = kgv(this.getDenominator(), addend.getDenominator());
        int new_n = this.getNumerator() * kgv / this.getDenominator()
                + addend.getNumerator() * kgv / addend.getDenominator();
        return new Fraction(new_n, kgv);
    }

    /**
     * subtracts another fraction from this fraction
     *
     * @param subtrahend the fraction to subtract
     * @return the difference
     */
    public Fraction subtract(Fraction subtrahend)
    {
        if (this.getDenominator() == subtrahend.getDenominator())
        {
            int new_n = this.getNumerator() - subtrahend.getNumerator();
            return new Fraction(new_n, this.getDenominator());
        }
        int kgv = kgv(this.getDenominator(), subtrahend.getDenominator());
        int new_n = this.getNumerator() * kgv / this.getDenominator()
                - subtrahend.getNumerator() * kgv / subtrahend.getDenominator();
        return new Fraction(new_n, kgv);
    }

    /**
     * Tries to create a Fraction from a given string representation
     * It checks whether the fraction matches a given regular expression
     * to allow for some variability in the input
     *
     * @param frac string representation of the fraction
     * @return the fraction created from {@param frac}
     */
    @SuppressWarnings("Duplicates")
    public static Fraction parseFraction(String frac)
    {
        if (!frac.matches("\\(?\\s?-?\\s?\\d+\\s?/\\s?\\d+\\s?\\)?"))
        {
            throw new IllegalArgumentException("The string entered was not a recognised fraction.");
        }
        String[] nums = frac.replaceAll("[()\\s]", "").split("/");
        int numerator = Integer.parseInt(nums[0]);
        int denominator = Integer.parseInt(nums[1]);

        return new Fraction(numerator, denominator);
    }

    /**
     * Hilfsfunktion zum Brueche kuerzen. Berechnet den ggt.
     *
     * @param x die erste Zahl
     * @param y die zweite Zahl
     * @return ggt
     */
    protected int ggt(int x, int y)
    {
        x = Math.abs(x);
        y = Math.abs(y);
        while (y != 0)
        {
            if (x > y)
            {
                x = x - y;
            }
            else
            {
                y = y - x;
            }
        }
        return x;
    }

    /**
     * since we need to equalise the denominators of the fractions to
     * subtract or add them we need to find the least common multiplicative
     *
     * @param n first number
     * @param m second number
     * @return the lcm of the two given numbers
     */
    protected int kgv(int n, int m)
    {
        return n * m / ggt(n, m);
    }

    /**
     * Gibt den Bruch formatiert aus
     *
     * @return Bruch in Schriftform
     */
    public String toString()
    {
        return "( " + numerator + " / " + denominator + " )";
    }

    /**
     * Returns the value of the specified number as an {@code int},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code int}.
     */
    @Override
    public int intValue()
    {
        return this.numerator / this.denominator;
    }

    /**
     * Returns the value of the specified number as a {@code long},
     * which may involve rounding or truncation.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code long}.
     */
    @Override
    public long longValue()
    {
        return (long) this.numerator / this.denominator;
    }

    /**
     * Returns the value of the specified number as a {@code float},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code float}.
     */
    @Override
    public float floatValue()
    {
        return ((float) this.numerator) / ((float) this.denominator);
    }

    /**
     * Returns the value of the specified number as a {@code double},
     * which may involve rounding.
     *
     * @return the numeric value represented by this object after conversion
     * to type {@code double}.
     */
    @Override
    public double doubleValue()
    {
        return ((double) this.numerator) / ((double) this.denominator);
    }
}
