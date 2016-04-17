package jft.sandbox;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

/**
 * Created by Anna on 12.04.16.
 */
public class Point {

    Double x;
    Double y;

    public Point(Double x, Double y){
        this.x=x;
        this.y=y;
    }

    public Point(){
        this.x=0.0;
        this.y=0.0;
    }

    public Double calculateDistance(Point secPoint){
        return sqrt(pow(secPoint.x - this.x,2) + pow(secPoint.y - this.y, 2));
    }

}
