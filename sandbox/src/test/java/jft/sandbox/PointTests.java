package jft.sandbox;


import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Anna on 17.04.16.
 */
public class PointTests {

    @Test
    public void testDistance(){

        Point p1 = new Point(3.0, 8.0);
        Point p2 = new Point(3.0, -12.0);
        Assert.assertEquals(p1.calculateDistance(p2), 20.0);

        Point p3 = new Point(-45.0, 8.0);
        Assert.assertEquals(p3.calculateDistance(p1), 48.0);

        Point p4 = new Point(23.56, -123.62);
        Double distance = (double)Math.round(p2.calculateDistance(p4) * 100d) / 100d;
        Assert.assertEquals(distance, 113.5);

    }
}
