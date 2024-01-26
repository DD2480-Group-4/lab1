package decide;

import java.awt.geom.Point2D;

public class Utilities {
 
    public static double calculateTriangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        return Math.abs((p1.x*(p2.y-p3.y) + p2.x*(p3.y-p1.y) + p3.x*(p1.y-p2.y))/2);
    }

}
