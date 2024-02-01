package decide;


import java.awt.geom.Point2D;

public class Main {
    private static final int minimumLength = 15;

	public static void main(String[] args) {
        // Input for DECIDE()
        Point2D.Double[] POINTS = {
                new Point2D.Double(2, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 3),
                new Point2D.Double(1, 1),
                new Point2D.Double(2, 2),
                new Point2D.Double(-2, 0),
                new Point2D.Double(-3, 0)};
        Parameters PARAMETERS = new Parameters(
                2, 10, 1, 10, 0,
                2, 2, 0, 7, 1,
                3, 0, 1, 2, 1,
                2, 1, 2, 1);
        LCMOperators[][] LCM = {
                {LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ORR, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ANDD,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.NOTUSED,
                        LCMOperators.ORR, LCMOperators.ORR, LCMOperators.NOTUSED,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ORR,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.NOTUSED,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ORR},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ANDD, LCMOperators.ANDD,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.NOTUSED, LCMOperators.ANDD, LCMOperators.ORR},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.ANDD,
                        LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.ORR,
                        LCMOperators.NOTUSED, LCMOperators.ANDD, LCMOperators.ORR},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ANDD,
                        LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                        LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ORR,
                        LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD}};
        boolean[] PUV = {
                true, true, false, true, true,
                true, false, true, false, false,
                true, true, false, true, true};
		DECIDE(POINTS.length, POINTS, PARAMETERS, LCM, PUV);
	}

	public static void DECIDE(int NUMPOINTS, Point2D.Double[] POINTS, Parameters PARAMETERS, LCMOperators[][] LCM, boolean[] PUV) {
        if (LCM.length < minimumLength) {
            int columnLength = (LCM.length > 0 && LCM[0] != null) ? LCM[0].length : 0;
            System.err.println("ERROR: LCM does not follow the dimension specification (15x15). " +
                               "Found: (" + LCM.length + "x" + columnLength + ").");
            return;
        }
        if (PUV.length < minimumLength) {
            System.err.print("ERROR: PUV does not follow the dimension specification (15). " +
                             "Found: (" + PUV.length + ").");
            return;
        }

        CMV cmvCalculator = new CMV(NUMPOINTS, POINTS, PARAMETERS);
        boolean[] cmv = cmvCalculator.calcCMV();
        boolean[][] pum = Launch.calcPUM(LCM, cmv);
        boolean[] fuv = Launch.calcFUV(pum, PUV);

        for (boolean b : fuv) {
            if (!b) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

}
