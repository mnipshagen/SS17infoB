package Blatt04.Ex01;

/**
 * A class to make use of the Fraction class.
 * It takes in arguments when started to calculate with two fractions.
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Calculator {

    @SuppressWarnings("Duplicates")
    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException
                        ("Please provide exactly three arguments.");
            }

            boolean isFrac1 = identify(args[0]);
            boolean isFrac2 = identify(args[2]);
            boolean fracOut = isFrac1 && isFrac2;

            Fraction f1,f2;
            String s1,s2;

            if (isFrac1) {
                f1 = Fraction.parseFraction(args[0]);
                s1 = f1.toString();
            } else {
                double d1 = Double.parseDouble(args[0]);
                int tmp = tenth(d1);
                f1 = new Fraction(((int) (d1 * Math.pow(10,tmp))),(int) Math.pow(10,tmp));
                s1 = args[0];
            }
            if (isFrac2) {
                f2 = Fraction.parseFraction(args[2]);
                s2 = f2.toString();
            } else {
                double d2 = Double.parseDouble(args[2]);
                int tmp = tenth(d2);
                f2 = new Fraction(((int) (d2 * Math.pow(10,tmp))),(int) Math.pow(10,tmp));
                s2 = args[2];
            }


            switch (args[1]) {
                case "*":
                    System.out.println("Multiplying " + s1 +
                            " with " + s2 + " results in:");
                    if (fracOut) {
                        System.out.println(f1.multiply(f2).toString());
                    } else {
                        Fraction f3 = f1.multiply(f2);
                        System.out.println(((double)f3.getNumerator()) / ((double) f3.getDenominator()));
                    }
                    break;
                case "/":
                    System.out.println("Dividing " + s1 +
                            " by " + s2 + " results in:");
                    if (fracOut) {
                        System.out.println(f1.divide(f2).toString());
                    } else {
                        Fraction f3 = f1.divide(f2);
                        System.out.println(((double)f3.getNumerator()) / ((double) f3.getDenominator()));
                    }
                    break;
                case "+":
                    System.out.println("Summing up " + s1 +
                            " and " + s2 + " results in:");
                    if (fracOut) {
                        System.out.println(f1.add(f2).toString());
                    } else {
                        Fraction f3 = f1.add(f2);
                        System.out.println(((double)f3.getNumerator()) / ((double) f3.getDenominator()));
                    }
                    break;
                case "-":
                    System.out.println("Subtracting " + s2 +
                            " from " + s1 + " results in:");
                    if (fracOut) {
                        System.out.println(f1.subtract(f2).toString());
                    } else {
                        Fraction f3 = f1.subtract(f2);
                        System.out.println(((double)f3.getNumerator()) / ((double) f3.getDenominator()));
                    }
                    break;
                default:
                    throw new IllegalArgumentException
                            ("No supported arithmetical expression found. " +
                                    "Only supported operands are: +, -, *, /");
            }
        } catch (Exception e){
                printInstruction(e);
        }
    }

    private static int tenth(double d) {
        String text = Double.toString(Math.abs(d));
        int integerPlaces = text.indexOf('.');
        return text.length() - integerPlaces - 1;
    }

    private static boolean identify(String num) {
        try{
            Fraction.parseFraction(num);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Whenever the program runs into an error, this method will be called
     * It prints out a formatted instruction text on how to use this program
     * @param e the exception the program ran into.
     */
    private static void printInstruction(Exception e) {
        System.err.println("Oh no! An exception occurred:\n" + e.getMessage()
                + "\n\nWith the following Stacktrace:");
        e.printStackTrace();
        System.out.print(
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~  Instructions  ~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~ This calculator supports two fractions, integers\n"
        +       "~ or doubles connected by the following operands:\n"
        +       "~ + - * /\n"
        +       "~ If you have whitespaces in an argument you have\n"
        +       "~ to enclose it in quotation marks (\"\")\n"
        +       "~ The \"*\" symbol must also be in quotation marks\n"
        +       "~ Examples for supported Fraction styles:\n"
        +       "~ \"( 3 / 4 )\" ; 3/4 ; (3/4 ; \"3 / 4)\"\n"
        +       "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~    Examples    ~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~ 3/4 + 7/8 ; \"7 / 8\" \"*\" (5/6) ; \"(7 /9\" - 2/1\n"
        +       "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        );

    }
}
