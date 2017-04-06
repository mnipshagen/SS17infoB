package Aufgabe4;

import Aufgabe4.Boat;

/**
 * Represents boats driven by a motor engine.
 *
 * @author Mathias Menninghaus
 */
public class MotorBoat extends Boat {
    public String type = "motorboat";

    public String getType() {
        return type;
    }

    public String getBoatClass() {
        return super.getBoatClass();
    }
}