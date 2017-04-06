package Aufgabe3;

/**
 * Created by Jannik on 17.04.2016.
 */
public class HungerGames {
    public static int getArea(double x, double y){

        double theta = Math.toDegrees(Math.atan2(x,y));
        double r = Math.sqrt((x*x) + (y*y));

        if(theta < 0) theta += 360;

        if (r > 1.5d) {
            return -1;
        }

        if (  0.0 <= theta && theta <  30.0 ) return 1;
        if ( 30.0 <= theta && theta <  60.0 ) return 2;
        if ( 60.0 <= theta && theta <  90.0 ) return 3;
        if ( 90.0 <= theta && theta < 120.0 ) return 4;
        if (120.0 <= theta && theta < 150.0 ) return 5;
        if (150.0 <= theta && theta < 180.0 ) return 6;
        if (180.0 <= theta && theta < 210.0 ) return 7;
        if (210.0 <= theta && theta < 240.0 ) return 8;
        if (240.0 <= theta && theta < 270.0 ) return 9;
        if (270.0 <= theta && theta < 300.0 ) return 10;
        if (300.0 <= theta && theta < 330.0 ) return 11;
        else return 12;

    }
}
