package Blatt02.Ex04;

/**
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 */
public class Calculator {

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new IllegalArgumentException
                        ("Please provide exactly three arguments.");
            }
            Fraction f1, f2;

            f1 = Fraction.parseFraction(args[0]);
            f2 = Fraction.parseFraction(args[2]);
            switch (args[1]) {
                case "*":
                    System.out.println("Multiplying " + f1.toString() +
                            " with " + f2.toString() + " results in: \n" +
                            f1.multiply(f2).toString());
                    break;
                case "/":
                    System.out.println("Dividing " + f1.toString() +
                            " by " + f2.toString() + " results in: \n" +
                            f1.divide(f2).toString());
                    break;
                case "+":
                    System.out.println("Summing up " + f1.toString() +
                            " and " + f2.toString() + " results in: \n" +
                            f1.add(f2).toString());
                    break;
                case "-":
                    System.out.println("Subtracting " + f2.toString() +
                            " from " + f1.toString() + " results in: \n" +
                            f1.subtract(f2).toString());
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

    private static void printInstruction(Exception e) {
        System.err.println("Oh no! An exception occurred:\n" + e.getMessage()
                + "\n\nWith the following Stacktrace:");
        e.printStackTrace();
        System.out.print(
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~  Instructions  ~~~~~~~~~~~~~~~~~\n"
        +       "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
        +       "~ This calculator supports only fractions and\n"
        +       "~ the following mathematical operands:\n"
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
