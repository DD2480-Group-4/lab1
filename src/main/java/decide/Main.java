package decide;


import java.awt.geom.Point2D;

public class Main {

	public static void main(String[] args) {
		Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
		Point2D.Double[] POINTS = new Point2D.Double[0];
		LCMOperators[][] LCM = new LCMOperators[15][15];
		boolean[] PUV = new boolean[15];
		DECIDE(POINTS.length, POINTS, PARAMETERS, LCM, PUV);
	}

	public static void DECIDE(int NUMPOINTS, Point2D.Double[] POINTS, Parameters PARAMETERS, LCMOperators[][] LCM, boolean[] PUV) {}

}
