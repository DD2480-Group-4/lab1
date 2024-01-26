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

    public boolean LIC0() {
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
