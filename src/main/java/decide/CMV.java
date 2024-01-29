package decide;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class CMV {
    private final int NUMPOINTS;
    private final Point2D.Double[] POINTS;
    private final Parameters PARAMETERS;

    public CMV(int NUMPOINTS, Point2D.Double[] POINTS, Parameters PARAMETERS) {
        this.NUMPOINTS = NUMPOINTS;
        this.POINTS = POINTS;
        this.PARAMETERS = PARAMETERS;
    }

    public boolean[] calcCMV() {
        return new boolean[]{
            LIC0(), LIC1(), LIC2(), LIC3(), LIC4(),
            LIC5(), LIC6(), LIC7(), LIC8(), LIC9(),
            LIC10(), LIC11(), LIC12(), LIC13(), LIC14()
        };
    }

    /**
     * LIC0: Function that verifies that at least one set of two consecutive
     * data points in POINTS are a distance greater than PARAMETERS.LENGTH1
     * apart.
     *
     * @return true if distance between two consecutive data points is
     *         greater than LENGTH1, false otherwise
     */
    public boolean LIC0() {
        for(int i = 0; i < NUMPOINTS-1; i++) {
            if(Point2D.distance(POINTS[i].x, POINTS[i].y, POINTS[i+1].x, POINTS[i+1].y) > PARAMETERS.LENGTH1()) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC1: Function that verifies that at least one set of three consecutive
     * data points in POINTS cannot all be contained within or on a circle of radius PARAMETERS.RADIUS1.
     *
     * @return true if the circumradius of three consecutive data points is
     *         greater than RADIUS1, false otherwise
     */
    public boolean LIC1() {

        for(int i = 0; i < NUMPOINTS - 2; i++)
        {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i+1];
            Point2D.Double p3 = POINTS[i+2];

            double a = p1.distance(p2);
            double b = p2.distance(p3);
            double c = p3.distance(p1);

            double area = Utilities.calculateTriangleArea(p1, p2, p3);

            double circumradius = (a * b * c) / (4 * area);

            if (circumradius > PARAMETERS.RADIUS1()) {
                return true;
            }
        }
        return false;
    }

    /**
     * LIC2: Function that verifies that there exists a set of three consecutive data points
     * that forms and angle such that
     * angle < (PI - PARAMETERS.EPSILON)
     * or
     * angle > (PI + PARAMETERS.EPSILON)
     *
     * @return true if the angle of the three consecutive data points is greater than PI + EPSILON
     *         or lesser than PI - EPSILON, false otherwise
     */
    public boolean LIC2() {

        double PI = 3.1415926535;

        for(int i = 0; i < NUMPOINTS - 2; i++)
        {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i+1];
            Point2D.Double p3 = POINTS[i+2];

            if (p2 == p1 || p2 == p3) {
                continue;
            }

            double vector1x = p1.x - p2.x;
            double vector1y = p1.y - p2.y;

            double vector2x = p3.x - p2.x;
            double vector2y = p3.y - p2.y;

            double dotProduct = vector1x * vector2x + vector1y * vector2y;

            double magnitude1 = Math.sqrt(vector1x * vector1x + vector1y * vector1y);
            double magnitude2 = Math.sqrt(vector2x * vector2x + vector2y * vector2y);

            double cosTheta = dotProduct / (magnitude1 * magnitude2);

            double angle = Math.acos(cosTheta);

            if (angle < (PI - PARAMETERS.EPSILON()) || angle > (PI + PARAMETERS.EPSILON()))
            {
                return true;
            }
        }

        return false;
    }

    public boolean LIC3() {

        for(int i = 0; i < NUMPOINTS - 2; i++)
        {
            double area = Utilities.calculateTriangleArea(POINTS[i], POINTS[i+1], POINTS[i+2]);

            if(area > PARAMETERS.AREA1())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC4: Function that verifies that there is at least one set of PARAMETERS.Q_PTS
     * consecutive data points that lie in more than PARAMETERS.QUADS quadrants.
     *
     * @return true if more than QUADS quadrants contain data points, false otherwise.
     */
    public boolean LIC4() {
        if (PARAMETERS.Q_PTS() < 2 || PARAMETERS.Q_PTS() > NUMPOINTS) {
            return false;
        }
        if (PARAMETERS.QUADS() < 1 || PARAMETERS.QUADS() > 3 || PARAMETERS.QUADS() > POINTS.length) {
            return false;
        }

        boolean[] filledQuadrants = new boolean[4];
        for (int i = 0; i < POINTS.length - PARAMETERS.Q_PTS() + 1; i++) {
            Arrays.fill(filledQuadrants, false);
            for (int j = i; j < i + PARAMETERS.Q_PTS(); j++) {
                int quadrantNumber = Utilities.getQuadrant(POINTS[j]);
                filledQuadrants[quadrantNumber - 1] = true;
            }

            int filledQuadrantsCounter = 0;
            for (boolean filled : filledQuadrants) {
                filledQuadrantsCounter += filled ? 1 : 0;
            }

            if(filledQuadrantsCounter > PARAMETERS.QUADS()) {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC5: Function that verifies that at least one set of two consecutive
     * data points in POINTS fulfills the condition that the x value of
     * the first point is greater than the x value second point.
     *
     * @return true if the x value of the first point is greater than
     *         the x value second point, false otherwise
     */
    public boolean LIC5() {

        for(int i = 0; i < NUMPOINTS - 1; i++)
        {
            if ((POINTS[i+1].x - POINTS[i].x) < 0)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Verifies the presence of {@link Parameters#N_PTS()} consecutive data points where at least one of them is further
     * away than {@link Parameters#DIST()} from either:
     * 1. The line formed between the first and the last points in the sequence of consecutive data points,
     *    if they are different.
     * 2. The summarised distance from the first point in the sequence of consecutive data points
     *    to every other point in the sequence (except the last one, since it would be 0),
     *    if the first and last points are the same.
     *
     * @return True if a point matching the conditions above are met.
     *         Returns false if no such point is found, or if {@link Parameters#N_PTS()} < 3,
     *         {@link Parameters#DIST()} < 0, or {@link Parameters#N_PTS()} > NUMPOINTS
     */
    public boolean LIC6() {
        int n_pts = PARAMETERS.N_PTS();
        double dist = PARAMETERS.DIST();
        if (n_pts < 3 || n_pts > NUMPOINTS || dist < 0) {
            return false;
        }
        int pointLimit = NUMPOINTS - n_pts + 1;
        for (int firstPointIndex = 0; firstPointIndex < pointLimit; firstPointIndex++) {
            var firstPoint = POINTS[firstPointIndex];
            int lastPointIndex = firstPointIndex+n_pts-1;
            var lastPoint = POINTS[lastPointIndex];
            if (firstPoint.equals(lastPoint)) {
                //We skip the first and last points because their distance to the coincident point will be 0.
                var totalDist = Arrays.stream(POINTS, firstPointIndex+1, lastPointIndex).mapToDouble(
                    point -> point.distance(firstPoint)
                ).reduce(Double::sum).orElse(0);
                if (totalDist > dist) {
                    return true;
                }
            } else {
                var line = new Line2D.Double(firstPoint, lastPoint);
                for (int i = firstPointIndex; i < lastPointIndex; i++) {
                    if (line.ptLineDist(POINTS[i]) > dist) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean LIC7() {

        if(NUMPOINTS < 3)
        {
            return false;
        }

        for(int i = 0; i < NUMPOINTS - PARAMETERS.K_PTS() -1; i++)
        {
            if(POINTS[i].distance(POINTS[i + PARAMETERS.K_PTS() + 1]) > PARAMETERS.LENGTH1())
            {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC8: Function that verifies that there exists at least one set of three data
     * points separated by exactly PARAMETERS.A_PTS and PARAMETERS.B_PTS consecutive
     * intervening points. These cannot be contained within a circle of radius
     * PARAMETERS.RADIUS1.
     *
     * @return true if there is a set of three data points that satisfies
     *         the above conditions, false otherwise.
     */
    public boolean LIC8() {
        if (NUMPOINTS < 5) {
            return false;
        }
        if (PARAMETERS.A_PTS() < 1 || PARAMETERS.B_PTS() < 1) {
            return false;
        }
        if (PARAMETERS.A_PTS() + PARAMETERS.B_PTS() > NUMPOINTS - 3) {
            return false;
        }

        for (int i = 0; i < NUMPOINTS - (PARAMETERS.A_PTS() + PARAMETERS.B_PTS() + 2); i++) {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i + PARAMETERS.A_PTS() + 1];
            Point2D.Double p3 = POINTS[i + PARAMETERS.A_PTS() + PARAMETERS.B_PTS() + 2];

            double a = p1.distance(p2);
            double b = p2.distance(p3);
            double c = p3.distance(p1);

            double area = Utilities.calculateTriangleArea(p1, p2, p3);
            double circumradius = (a * b * c) / (4 * area);

            if (circumradius > PARAMETERS.RADIUS1()) {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC9: Function that verifies that at least one set of three data points
     * separated by exactly PARAMETERS.C_PTS and PARAMETERS.D_PTS
     * consecutive intervening points, respectively, forms and angle such that
     * angle < (PI - PARAMETERS.EPSILON)
     * or
     * angle > (PI + PARAMETERS.EPSILON)
     *
     * @return true if the angle of the three data points is greater than PI + EPSILON
     *         or lesser than PI - EPSILON, false if NUMPOINTS < 5 or otherwise
     */
    public boolean LIC9() {

        if (NUMPOINTS < 5)
        {
            return false;
        }

        double PI = 3.1415926535;

        for(int i = 0; i < NUMPOINTS - (PARAMETERS.C_PTS() + PARAMETERS.D_PTS() + 2); i++)
        {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i + PARAMETERS.C_PTS() + 1];
            Point2D.Double p3 = POINTS[i + PARAMETERS.C_PTS() + PARAMETERS.D_PTS() + 2];

            if (p2 == p1 || p2 == p3) {
                continue;
            }

            double vector1x = p1.x - p2.x;
            double vector1y = p1.y - p2.y;

            double vector2x = p3.x - p2.x;
            double vector2y = p3.y - p2.y;

            double dotProduct = vector1x * vector2x + vector1y * vector2y;

            double magnitude1 = Math.sqrt(vector1x * vector1x + vector1y * vector1y);
            double magnitude2 = Math.sqrt(vector2x * vector2x + vector2y * vector2y);

            double cosTheta = dotProduct / (magnitude1 * magnitude2);

            double angle = Math.acos(cosTheta);

            if (angle < (PI - PARAMETERS.EPSILON()) || angle > (PI + PARAMETERS.EPSILON()))
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds all 3 consecutive points, where the first 2 have {@link Parameters#E_PTS()} points between them and
     * the last 2 have {@link Parameters#F_PTS()} points between them.
     * Then verifies that at least one of these trios of points forms a triangle that has an area larger than
     * {@link Parameters#AREA1()}.
     *
     * @return True if the above conditions are met.
     *         False if not, or if NUMPOINTS < 5, {@link Parameters#E_PTS()} < 1, {@link Parameters#F_PTS()} < 1 or if
     *         {@link Parameters#E_PTS()} + {@link Parameters#F_PTS()} > NUMPOINTS - 3
     */
    public boolean LIC10() {
        return Utilities.checkPointsForTriangle(
                POINTS, NUMPOINTS, PARAMETERS.E_PTS(), PARAMETERS.F_PTS(), area -> area > PARAMETERS.AREA1()
        );
    }

    public boolean LIC11() {

        if(NUMPOINTS < 3) {
            return false;
        }
        
        for(int i = 0; i < NUMPOINTS - PARAMETERS.G_PTS() - 1; i++) {
            if(POINTS[i + PARAMETERS.G_PTS() + 1].getX() - POINTS[i].getX() < 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * LIC12: Function that verifies that there is at least one set of consecutive
     * points separated by PARAMETERS.K_PTS apart where the distance between them
     * exceeds PARAMETERS.LENGTH1. Also, there exists a similar pair where the
     * distance exceeds PARAMETERS.LENGTH2.
     *
     * @return true if both of the conditions mentioned above hold, false otherwise
     */
    public boolean LIC12() {
        if (NUMPOINTS < 3) {
            return false;
        }
        if (PARAMETERS.LENGTH2() < 0) {
            return false;
        }

        boolean cond1 = false, cond2 = false;
        for (int i = 0; i < POINTS.length - PARAMETERS.K_PTS() - 1; i++) {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i + PARAMETERS.K_PTS() + 1];
            if (p1.distance(p2) > PARAMETERS.LENGTH1()) {
                cond1 = true;
            }
            if (p1.distance(p2) < PARAMETERS.LENGTH2()) {
                cond2 = true;
            }
        }

        return cond1 && cond2;
    }

    /**
     * LIC13: Function that verifies that at least one set of three data points
     * separated by exactly PARAMETERS.A_PTS and PARAMETERS.B_PTS
     * consecutive intervening points, respectively, cannot all be contained
     * within or on a circle of radius PARAMETERS.RADIUS1.
     * Also verifies that at least one set of three data points given the same
     * constrictions, can all be contained within or on a circle of radius PARAMETERS.RADIUS2.
     *
     * @return true if the circumradius of a set of three data points is
     *         greater than RADIUS1 and the circumradius of a set of three data points is
     *         lesser or equal to RADIUS2 , false if NUMPOINTS < 5 or otherwise
     */
    public boolean LIC13() {

        if (NUMPOINTS < 5)
        {
            return false;
        }

        boolean satisfiesR1 = false;
        boolean satisfiesR2 = false;

        for(int i = 0; i < NUMPOINTS - (PARAMETERS.A_PTS() + PARAMETERS.B_PTS() + 2); i++) {
            Point2D.Double p1 = POINTS[i];
            Point2D.Double p2 = POINTS[i + PARAMETERS.A_PTS() + 1];
            Point2D.Double p3 = POINTS[i + PARAMETERS.A_PTS() + PARAMETERS.B_PTS() + 2];

            double a = p1.distance(p2);
            double b = p2.distance(p3);
            double c = p3.distance(p1);

            double area = Utilities.calculateTriangleArea(p1, p2, p3);

            double circumradius = (a * b * c) / (4 * area);

            if (circumradius > PARAMETERS.RADIUS1()) {
                satisfiesR1 = true;
            }

            if (circumradius <= PARAMETERS.RADIUS2()) {
                satisfiesR2 = true;
            }

            if (satisfiesR1 && satisfiesR2) {
                return true;
            }
        }

        return false;
    }

    /**
     * Finds all 3 consecutive points, where the first 2 have {@link Parameters#E_PTS()} points between them and
     * the last 2 have {@link Parameters#F_PTS()} points between them.
     * Then verifies that at least one of these trios of points forms a triangle that has an area larger than
     * {@link Parameters#AREA1()}, and that at least one of them has an area smaller than {@link Parameters#AREA2()}.
     *
     * @return True if the above conditions are met.
     *         False if not, or if NUMPOINTS < 5, {@link Parameters#E_PTS()} < 1, {@link Parameters#F_PTS()} < 1,
     *         {@link Parameters#E_PTS()} + {@link Parameters#F_PTS()} > NUMPOINTS - 3, or if {@link Parameters#AREA2()} < 0.
     */
    public boolean LIC14() {
        if (PARAMETERS.AREA2() < 0) {
            return false;
        }
        return Utilities.checkPointsForTriangle(
                POINTS, NUMPOINTS, PARAMETERS.E_PTS(), PARAMETERS.F_PTS(), area -> area > PARAMETERS.AREA1()
        ) && Utilities.checkPointsForTriangle(
                POINTS, NUMPOINTS, PARAMETERS.E_PTS(), PARAMETERS.F_PTS(), area -> area < PARAMETERS.AREA2()
        );
    }

}
