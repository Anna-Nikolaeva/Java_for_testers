package jft.homework1;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


import java.util.Scanner;

public class first {

    public static Double calculateDistance(Point first, Point second){

        return sqrt(pow(second.x - first.x,2) + pow(second.y - first.y, 2));
    }

   public static void main(String[] args) { 

       Point firstPoint = new Point(5.0, 54.0);
       Point secondPoint = new Point(-5.0, 39.0);
       System.out.format("Distance = %.2f %n",calculateDistance(firstPoint,secondPoint));// separate function

       Point onePoint = new Point(3.0, 2.0);
       System.out.format("Distance = %.2f %n",onePoint.calculateDistance(secondPoint));//method within class

       Point fPoint = new Point();
       Point sPoint = new Point();
       Scanner in = new Scanner(System.in);
       System.out.print("Enter First X:");
       fPoint.x = in.nextDouble();
       System.out.print("Enter First Y:");
       fPoint.y = in.nextDouble();
       System.out.print("Enter Second X:");
       sPoint.x = in.nextDouble();
       System.out.print("Enter Second Y:");
       sPoint.y = in.nextDouble();
       System.out.format("Distance = %.2f %n",calculateDistance(fPoint,sPoint));//calculate from keyboard
   }
}