package Aufgabe2;

/**
 * Eine Testklasse, die alle Funktionen von <code>Fraction</code> testet
 */
public class FractionTest {

    public static void main(String[] args) {

        Fraction f1 = new Fraction(2, 3);
        Fraction f2 = new Fraction(4, 10);
        Fraction f3 = new Fraction(9, 90);
        Fraction g = new Fraction(5);

        // Alle Brueche werden auf korrektes kuerzen ueberprueft.
        System.out.println("Überprüfe korrekte Instanziierung:");

        if (f1.getNumerator() == 2
                && f1.getDenominator() == 3) {
            System.out.println("f1 korrekt.");
        }
        else {
            System.out.print("f1 Fehler! f1 = " + f1.toString());
        }

        if (f2.getNumerator() == 2
                && f2.getDenominator() == 5) {
            System.out.println("f2 korrekt.");
        }
        else {
            System.out.print("f2 Fehler! f2 = " + f2.toString());
        }

        if (f3.getNumerator() == 1
                && f3.getDenominator() == 10){
            System.out.println("f3 korrekt.");
        }
        else {
            System.out.print("f3 Fehler! f3 = " + f3.toString());
        }

        if (g.getNumerator() == 5
            && g.getDenominator() == 1) {
            System.out.println("g korrekt.");
        }
        else {
            System.out.print("g Fehler! g = " + g.toString());
        }

    // Alle Rechenoperationen werden einmal durchgefuehrt und es
    // wird verglichen ob alle Ergebnisse korrekt sind. Falls nicht,
    // werden "ist" und "sollte" Ergebnis angezeigt um Fehler zu finden.
        System.out.println();
        System.out.println("Teste Rechenaufgaben:");

        Fraction p1 = f1.multiply(4);
        if(p1.getNumerator() == 8) {
            System.out.println("Ganzzahlige Multiplikation: Check!");
        }
        else
                System.out.println("Ganzzahlige Multiplikation fehlgeschlagen");

        Fraction p2 = f2.multiply(f3);
        if(p2.getNumerator() == 1 && p2.getDenominator() == 25) {
            System.out.println("Faktorielle Multiplikation: Check");
        }
        else
            System.out.println("Faktorielle Multiplikation: Nope. "
            + "Sollte sein: 1/25. Ist: " + p2.toString());

        Fraction p3 = f1.divide(f2);
        if(p3.getNumerator() == 5 && p3.getDenominator() == 3) {
            System.out.println("Division: Oh Yeah!");
        }
        else
            System.out.println("Division: Och neee. Sollte sein:"
            + "5/3. Ist aber: " + p3.toString());

        Fraction p4 = f1.multiply(f2, f3, g);
        if(p4.getNumerator() == 2 && p4.getDenominator() == 15) {
            System.out.println("Multiple Orgasmen, eeehhm, "
                + "Multiplikation: Ohhhh jaaaa!");
        }
        else
            System.out.println("Multiple Multiplikation "
            + "Massiv Manipuliert! Sollte sein: 2/15. "
            + "Ist aber: " + p4.toString());
    }

}
