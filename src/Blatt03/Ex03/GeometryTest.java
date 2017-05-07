package Blatt03.Ex03;

import java.util.Arrays;

/**
 * A class to test the several functions of the geometry class-set
 * @author Moritz Nipshagen
 * @author Tobias Ludwig
 * @version probably final
 */
public class GeometryTest {

    public static void main(String[] args) {
        // initialising all the things
        Point p1 = new Point(3.0, -3.0, 0., 0.);
        Point p2 = new Point(0, 0, -3, 3);
        Point2D p2D1 = new Point2D(0,0);
        Point2D p2D2 = new Point2D(1,1);
        Volume v1 = new Volume(p1, p2);
        Volume v2 = new Volume(new Point(6, 6, 6, 6), new Point(0,0,0,0));
        Rectangle r1 = new Rectangle(p2D1, p2D2);
        Rectangle r2 = new Rectangle(new Point2D(0,0), new Point2D(-1,-1));

        // will turn true whenever something did not turn out as expected
        boolean fail = true;

        // checking dimensional check
        try{
            new Point(1);
            System.out.println("Could initialise 1 dimensional Point.");
        } catch (Exception ignored) {}
        fail = false;

        // checking whether dimensions are all correct
        if (p1.dimensions() != 4) {
            fail = true;
            System.out.println("Is:" + p1.dimensions() + "; Should be 4");
        }
        if (p1.dimensions() != v1.dimensions()) {
            fail = true;
            System.out.println("Is:" + p1.dimensions() + " and " + v1.dimensions() + "; Should be 4");
        }
        if (p2D1.dimensions() != 2) {
            fail = true;
            System.out.println("Is:" + p2D1.dimensions() + "; Should be 2");
        }
        if (p2D1.dimensions() != r1.dimensions()) {
            fail = true;
            System.out.println("Is:" + p2D1.dimensions() + " and " + r1.dimensions() + "; Should be 2");
        }
        if  (fail) System.out.println("Dimensions went all wrong!");
        fail = false;

        // checking if volume calculation is on point
        if (v1.volume() != Math.pow(3,4)) {
            fail = true;
            System.out.println("Is: " + v1.volume() + "; Should: " + Math.pow(3,4));
        }
        if (v2.volume() != Math.pow(6,4)){
            fail = true;
            System.out.println("Is: " + v2.volume() + "; Should: " + Math.pow(6,4));
        }
        if (r1.volume() != 1.) {
            fail = true;
            System.out.println("Is: " + r1.volume() + "; Should: 1");
        }
        if (r2.volume() != 1.) {
            fail = true;
            System.out.println("Is: " + r2.volume() + "; Should: 1");
        }
        if (p1.volume() != 0) {
            fail = true;
            System.out.println("Is: " + p1.volume() + "; Should: 0");
        }
        if (p2D2.volume() != 0) {
            fail = true;
            System.out.println("Is: " + p2D2.volume() + "; Should: 0");
        }
        if (fail) {
            System.out.println("Volume calculation is off.");
        }
        fail = false;

        // testing compareTo
        try{
            r2.compareTo(null);
            fail = true;
            System.out.println("Comparing with null worked");
        } catch (Exception e) {
            fail = false;
        }
        try{
            r2.compareTo("not a string");
            fail = true;
            System.out.println("Comparing with non-geometry worked");
        } catch (Exception e) {
            fail = false;
        }
        if (v1.compareTo(v2) >= 0){
            fail = true;
            System.out.println("v1 should not be bigger than v2");
        }
        if (p1.compareTo(p2D2) != 0) {
            fail = true;
            System.out.println("p1 and p2d2 should have the same volume");
        }
        if (v2.compareTo(v1) <= 0) {
            fail = true;
            System.out.println("v2 should be bigger than v1");
        }
        if (r1.compareTo(r2) != r2.compareTo(r1)) {
            fail = true;
            System.out.println("r1 to r2 is not the same as r2 to r1");
        }
        if (v2.compareTo(p1) <= 0) {
            fail = true;
            System.out.println("v2 should not be smaller than p1");
        }
        if (fail) {
            System.out.println("compareTo did not work as expected");
        }
        fail = false;

        // checking encapsulate
        Geometry n1 = p1.encapsulate(p2);
        Geometry n2 = v2.encapsulate(p1);
        if (p2D1.encapsulate(v1) != null) {
            fail = true;
            System.out.println("Encapsulating of different dimensions is not null");
        }
        if (n1.volume() != v1.volume()) {
            fail = true;
            System.out.println("n1 does not have the same volume as v1");
            System.out.println("n1: " + n1.volume() + "; v1: " + v1.volume());
        }
        if (n2.volume() <= v2.volume()) {
            fail = true;
            System.out.println("n2 volume  should be bigger than v2");
            System.out.println("n2: " + n2.volume() + "; v2: " + v2.volume());
        }
        if (! Arrays.equals(((Volume) n2).getP1().getCoordinates(), v2.getP1().getCoordinates())) {
            fail = true;
            System.out.println("n2 should have same P1 coordinates to v2");
            System.out.println("n2: " + Arrays.toString(((Volume) n2).getP1().getCoordinates()) +
                                "\nv2: " + Arrays.toString(v2.getP1().getCoordinates()));
        }
        if (! Arrays.equals(((Volume) n2).getP2().getCoordinates(), new double[] {0., -3., 0., 0.})) {
            fail = true;
            System.out.println("n2 p2 coordinates are off");
            System.out.println("n2: " + Arrays.toString(((Volume) n2).getP2().getCoordinates()) + "\nshould be:" +
                                Arrays.toString(new double[] {0., -3., 0., 0.}));
        }
        if (fail) {
            System.out.println("Encapsulating did not work as expected!");
        }

        System.out.println("Done.");
    }
}
