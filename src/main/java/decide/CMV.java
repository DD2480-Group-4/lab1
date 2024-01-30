package decide;

import java.awt.geom.Point2D;

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

    public boolean LIC1() {
        return false;
    }

    public boolean LIC2() {
        return false;
    }

    public boolean LIC3() {
        return false;
    }

    public boolean LIC4() {
        return false;
    }

    public boolean LIC5() {
        return false;
    }

    public boolean LIC6() {
        return false;
    }

    public boolean LIC7() {
        return false;
    }

    public boolean LIC8() {
        return false;
    }

    public boolean LIC9() {
        return false;
    }

    public boolean LIC10() {
        return false;
    }

    public boolean LIC11() {
        return false;
    }

    public boolean LIC12() {
        return false;
    }

    public boolean LIC13() {
        return false;
    }

    public boolean LIC14() {
        return false;
    }

}
