package decide;

import java.awt.geom.Point2D;
import java.util.function.DoublePredicate;

public class Utilities {
 
    public static double calculateTriangleArea(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        return Math.abs((p1.x*(p2.y-p3.y) + p2.x*(p3.y-p1.y) + p3.x*(p1.y-p2.y))/2);
    }

    /**
     * Finds all 3 consecutive points, where the first 2 have firstSpacing points between them and
     * the last 2 have secondSpacing points between them.
     * Then verifies that at least one of these trios of points forms a triangle that matches a given condition.
     *
     * @param points The array containing the points.
     * @param numPoints The number of points in the array. Must not be larger than the size of the array.
     * @param firstSpacing The number of points between the first and the second point.
     * @param secondSpacing The number of points between the second and third point.
     * @param areaChecker Function returning true when the given triangle area meets the required condition.
     * @return True if the above conditions are met.
     *         False if not, or if numPoints < 5, firstSpacing < 1, secondSpacing < 1 or if
     *         firstSpacing + secondSpacing > numPoints - 3
     */
    public static boolean checkPointsForTriangle(
            Point2D.Double[] points, int numPoints, int firstSpacing, int secondSpacing, DoublePredicate areaChecker
    ) {
        if (numPoints < 5 || firstSpacing < 1 || secondSpacing < 1 || firstSpacing + secondSpacing > numPoints - 3) {
            return false;
        }
        int pointLimit = numPoints - (firstSpacing + secondSpacing + 3) + 1;
        for (int firstPoint = 0; firstPoint < pointLimit; firstPoint++) {
            int secondPoint = firstPoint + firstSpacing + 1;
            int thirdPoint = secondPoint + secondSpacing + 1;
            double area = Utilities.calculateTriangleArea(
                    points[firstPoint], points[secondPoint], points[thirdPoint]
            );
            if (areaChecker.test(area)) {
                return true;
            }
        }
        return false;
    }

}
