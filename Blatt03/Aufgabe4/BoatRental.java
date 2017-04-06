package Aufgabe4;

import Aufgabe4.Boat;

/**
 * A Class, representing the boat rental of knut hansen.
 *
 * @author Mathias Menninghaus
 */

public class BoatRental {

    public static void main(String[] args) {
        MotorBoat myMotorBoat = new MotorBoat();

        System.out.println("myMotorBoat.type             : " + myMotorBoat.type);
        System.out.println("((Aufgabe4.Boat)myMotorBoat).type     : " + ((Boat) myMotorBoat).type);
        System.out.println("myMotorBoat.getType()        : " + myMotorBoat.getType());
        System.out.println("myMotorBoat.getBoatClass()   : " + myMotorBoat.getBoatClass());

        Canoe myCanoe = new Canoe();
        System.out.println("myCanoe.type                 : " + myCanoe.type);
        System.out.println("myCanoe.getType()            : " + myCanoe.getType());
        System.out.println("myCanoe.getBoatClass()       : " + myCanoe.getBoatClass());

        Boat myBoat = myCanoe;
        System.out.println("myBoat.type                  : " + myBoat.type);
        System.out.println("myBoat.getType()             : " + myBoat.getType());
        System.out.println("((Aufgabe4.Canoe)myBoat).type         : " + ((Canoe) myBoat).type);

    }
}