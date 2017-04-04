/**
 * Authors: mnipshagen, toludwig
 * Speichert einen Bruch als 2 einzelne Zahlen <code>numerator</code>
 * und <code>denominator</code>. Es werden einzelne Rechenoperationen
 * unterstuetzt.
 */
public class Fraction {

    // Zaehler des Bruchs
    private int numerator;
    // Nenner des Bruchs
    private int denominator;

    /**
     * Konstruktor der den Bruch aus 2 uebergebenen Integers kreirt
     * @param n Zaehler
     * @param d Nenner
     */
    public Fraction(int n, int d){
        int ggt = ggt(n, d);
        this.numerator = n/ggt;
        this.denominator = d/ggt;
    }

    /**
     * Konstruktor fuer ganze zahlen, weil Integer nicht ins Schema passt
     * @param n Der Zaehler, der Nenner ist bei Ganzzahlen 1
     */
    public Fraction(int n){
        this(n, 1);
    }

    /**
     * gibt den Zaehler aus, damit die variable private bleibt
     * @return Numerator
     */
    public int getNumerator(){
        return this.numerator;
    }

    /**
     * Gibt den Nenner aus, damit die Variable private bleibt
     * @return denominator
     */
    public int getDenominator(){
        return this.denominator;
    }

    /**
     * Multipliziert den Bruch mit einer ganzen Zahl. whey.
     * @param factor der Multiplikator.
     * @return den multiplizierten Bruch
     */
    public Fraction multiply(int factor){
        int newnum = this.numerator * factor;

        Fraction f = new Fraction(newnum, this.getDenominator());

        return f;
    }

    /**
     * Multiplieziert mit einem anderen Bruch. n*n und d*d
     * @param factor der multiplikator Bruch
     * @return Das Produkt
     */
    public Fraction multiply(Fraction factor){
        int newnum = this.numerator * factor.getNumerator();
        int newdenom = this.denominator * factor.getDenominator();

        Fraction f = new Fraction(newnum, newdenom);

        return f;
    }

    /**
     * Multipliziert den Bruch mit vielen, vielen Bruechen
     * @param factors Liste mit Faktoren
     * @return Das (Bruttosozial)Produkt
     */
    public Fraction multiply(Fraction... factors){
        Fraction f =
                new Fraction(this.getNumerator(), this.getDenominator());

        for(Fraction fs : factors){
            f = f.multiply(fs);
        }

        return f;
    }

    /**
     * Durch Brueche teilen ist mit dem Kehrbruch mal nehmen. whey.
     * @param divisor Der zu drehende Bruch
     * @return Das Produkt der entstandenen Multiplikation
     */
    public Fraction divide(Fraction divisor){
        Fraction f =
                new Fraction(divisor.getDenominator(),
                        divisor.getNumerator());

        return this.multiply(f);
    }

    /**
     * Hilfsfunktion zum Brueche kuerzen. Berechnet den ggt.
     * @param x die erste Zahl
     * @param y die zweite Zahl
     * @return ggt
     */
    private int ggt(int x, int y) {
        while (y != 0) {
            if (x > y) {
                x = x - y;
            } else {
                y = y - x;
            }
        }
        return x;
    }

    /**
     * Gibt den Bruch formatiert aus
     * @return Bruch in Schriftform
     */
    public String toString() {
        return numerator + "/" + denominator;
    }
}
