package decide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;

public class CMVTests {

    /**
     * LIC0 Test:
     * Three points are given, all except the last is 4 units apart from the next point.
     * Parameter LENGTH1 is set to 3.
     * LIC0 is expected to return true.
     */
    @Test
    @DisplayName("LIC0: satisfied condition returns true")
    void LIC0_ConsecutiveDataPointsFurtherThanLength_ReturnsTrue()
    {
        Parameters PARAMETERS = new Parameters(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {new Point2D.Double(0, 0), new Point2D.Double(0, 4), new Point2D.Double(0, 8)};

        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC0()).isTrue();
    }

    /**
     * LIC0 Test:
     * Three points are given, all except the last is 4 units apart from the next point.
     * Parameter LENGTH1 is set to 5.
     * LIC0 is expected to return false.
     */
    @Test
    @DisplayName("LIC0: unsatisfied condition returns false")
    void LIC0_ConsecutiveDataPointsCloserThanLength_ReturnsFalse()
    {
        Parameters PARAMETERS = new Parameters(5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {new Point2D.Double(0, 0), new Point2D.Double(0, 4), new Point2D.Double(0, 8)};

        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC0()).isFalse();
    }

    /**
     * LIC1 Test:
     * Four points are given. The first three points form a circle with a radius of 12.5 units.
     * Parameter RADIUS1 is set to 5.
     * LIC1 is expected to return true.
     */
    @Test
    @DisplayName("LIC1: satisfied condition returns true")
    void LIC1_DataPointsCannotFitInCircle_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(10, 10),
                new Point2D.Double(-10, 10),
                new Point2D.Double(0, -10),
                new Point2D.Double(0, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC1()).isEqualTo(true);
    }

    /**
     * LIC1 Test:
     * Four points are given. 
     * The first three points form a circle with a radius of 0.13 units.
     * The last three points for a circle with a radius of 0.16 units.
     * Parameter RADIUS1 is set to 1.
     * LIC1 is expected to return false.
     */
    @Test
    @DisplayName("LIC1: unsatisfied condition returns false")
    void LIC1_DataPointsAlwaysFitInCircle_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0.1, 0.1),
                new Point2D.Double(-0.1, 0.1),
                new Point2D.Double(0, -0.1),
                new Point2D.Double(0, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC1()).isEqualTo(false);
    }

    /**
     * LIC2 Test:
     * Three points are given. Two of the points are the same, causing the angle to be undefined.
     * LIC2 is expected to return false.
     */
	@Test
	@DisplayName("LIC2: undefined angle returns false")
	void LIC2_UndefinedAngle_ReturnsFalse() {
		var points = new Point2D.Double[] {
				new Point2D.Double(0,1),
				new Point2D.Double(0,0),
				new Point2D.Double(0,0)
		};
		CMV cmv = new CMV(
				points.length, points, new Parameters(
						0,0,0,0,0,0,0,0,0,
						0,0,0,0,0,0,0,0,0,0
				)
		);
		Assertions.assertThat(cmv.LIC2()).isFalse();
	}

    /**
     * LIC2 Test:
     * Three points are given which forms an angle below PI/2.
     * Parameter EPSILON is set to PI/2.
     * LIC2 is expected to return true.
     */
	@Test
	@DisplayName("LIC2: satisfied general-case")
	void LIC2_GeneralCase_ReturnsTrue() {
		var points = new Point2D.Double[] {
				new Point2D.Double(1,0.001),
				new Point2D.Double(0,0),
				new Point2D.Double(0,1)
		};
		CMV cmv = new CMV(
				points.length, points, new Parameters(
				0,0,0,0,Math.PI/2,0,0,0,0,
				0,0,0,0,0,0,0,0,0,0
		)
		);
		Assertions.assertThat(cmv.LIC2()).isTrue();
	}

    /**
     * LIC2 Test:
     * Three points are given which forms an angle of PI/2.
     * Parameter EPSILON is set to PI/2.
     * LIC2 is expected to return false.
     */
	@Test
	@DisplayName("LIC2: unsatisfied general-case")
	void LIC2_GeneralCase_ReturnsFalse() {
		var points = new Point2D.Double[]{
				new Point2D.Double(1, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 1)
		};
		CMV cmv = new CMV(
				points.length, points, new Parameters(
				0, 0, 0, 0, Math.PI/2, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0, 0, 0, 0, 0
		)
		);
		Assertions.assertThat(cmv.LIC2()).isFalse();
	}

    /**
     * LIC3 Test:
     * Three points are given which forms a triangle with an area of 0.5 units. 
     * Parameter AREA1 is set to 0.49.
     * LIC3 is expected to return true.
     */
	@Test
	@DisplayName("LIC3: satisfied returns true")
	void LIC3_Simple_ReturnsTrue() {
		var points = new Point2D.Double[] {
			new Point2D.Double(1, 0),
			new Point2D.Double(0, 0),
			new Point2D.Double(0, 1)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				0,0,0,0,0, 0.49,0,0,0,
				0,0,0,0,0,0,0,0,0,0
		));
		Assertions.assertThat(cmv.LIC3()).isTrue();
	}

    /**
     * LIC3 Test:
     * Three points are given which forms a triangle with an area of 0.5 units.
     * Parameter AREA1 is set to 0.5.
     * LIC3 is expected to return false.
     */
	@Test
	@DisplayName("LIC3: unsatisfied returns false")
	void LIC3_Simple_ReturnsFalse() {
		var points = new Point2D.Double[] {
				new Point2D.Double(1, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 1)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				0,0,0,0,0, 0.5,0,0,0,
				0,0,0,0,0,0,0,0,0,0
		));
		Assertions.assertThat(cmv.LIC3()).isFalse();
	}

    /**
     * LIC4 Test:
     * Three points are given which lies quadrants 1, 3 and 4.
     * Parameters QUADS is set to 2 and QUADS is set to 3.
     * LIC4 is expected to return true.
     */
    @Test
    @DisplayName("LIC4: satisfied condition returns true")
    void LIC4_PointsLieInMoreThanQuadQuadrants_ReturnsTrue()
    {
        Point2D.Double[] POINTS = new Point2D.Double[] {
            new Point2D.Double(4,4),
            new Point2D.Double(4,-4),
            new Point2D.Double(-4,-4),
        };

        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC4()).isTrue();
    }

    /**
     * LIC4 Test:
     * Three points are given which lies quadrants 1, 3 and 4.
     * Parameters QUADS is set to 3 and QUADS is set to 3.
     * LIC4 is expected to return false.
     */
    @Test
    @DisplayName("LIC4: unsatisfied condition returns false")
    void LIC4_PointsLieInLessThanQuadQuadrants_ReturnsFalse()
    {
        Point2D.Double[] POINTS = new Point2D.Double[] {
            new Point2D.Double(4,4),
            new Point2D.Double(4,-4),
            new Point2D.Double(-4,-4),
        };

        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC4()).isFalse();
    }

    /**
     * LIC5 Test:
     * Two points are given. 
     * The first point is 1 unit ahead of the second on the x-axis.
     * LIC5 is expected to return true.
     */
	@Test
    @DisplayName("LIC5: satisfied condition returns true")
    void LIC5_distanceBetweenNextPointAndCurrentPointLessThanZero_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {
                new Point2D.Double(2, 0),
                new Point2D.Double(1, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC5()).isTrue();
    }

    /**
     * LIC5 Test:
     * Two points are given. 
     * The first point is 1 unit behind of the second on the x-axis.
     * LIC5 is expected to return true.
     */
    @Test
    @DisplayName("LIC5: unsatisfied condition returns false")
    void LIC5_distanceBetweenNextPointAndCurrentPointNotLessThanZero_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {
                new Point2D.Double(1, 0),
                new Point2D.Double(2, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC5()).isFalse();
    }

    /**
     * LIC6 Test:
     * Seven points are given.
     * The second and sixth point are joined on the line y = 0.
     * The fourth point is 10.1 units away from this line. 
     * The parameters DIST is set to 10 and N_PTS is set to 5.
     * LIC6 is expected to return true.
     */
	@Test
    @DisplayName("LIC6: satisfied first condition returns true")
    void LIC6_DataPointFurtherAwayThanDist_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,0,0,10,0,0,5,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(2, 2),
                new Point2D.Double(0, 10.1),
                new Point2D.Double(3, -3),
                new Point2D.Double(10, 0),
                new Point2D.Double(1, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC6()).isEqualTo(true);
    }

    /**
     * LIC6 Test:
     * Five points are given.
     * The first and fifth points are identical. 
     * The distance to all other points are 7 units. 
     * The parameters DIST is set to 5 and N_PTS is set to 5.
     * LIC6 is expected to return true.
     */
    @Test
    @DisplayName("LIC6: satisfied second condition returns true")
    void LIC6_SumDistDataPointFurtherAwayThanDist_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,0,0,5,0,0,5,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 2),
                new Point2D.Double(0, 3),
                new Point2D.Double(0, 2),
                new Point2D.Double(0, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC6()).isEqualTo(true);
    }

    /**
     * LIC6 Test:
     * Seven points are given.
     * The second and sixth point are joined on the line y = 0.
     * The fourth point is 10 units away from this line, no other point is further away than this. 
     * The parameters DIST is set to 10 and N_PTS is set to 5.
     * LIC6 is expected to return false.
     */
    @Test
    @DisplayName("LIC6: unsatisfied first condition returns false")
    void LIC6_NoDataPointFurtherAwayThanDist_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,0,0,10,0,0,5,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(2, 2),
                new Point2D.Double(3, 10),
                new Point2D.Double(3, -3),
                new Point2D.Double(10, 0),
                new Point2D.Double(1, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC6()).isEqualTo(false);
    }

    /**
     * LIC6 Test:
     * Five points are given.
     * The first and fifth points are identical. 
     * The distance to all other points are 5 units. 
     * The parameters DIST is set to 5 and N_PTS is set to 5.
     * LIC6 is expected to return false.
     */
    @Test
    @DisplayName("LIC6: unsatisfied second condition returns false")
    void LIC6_NotSumDistAllDataPointFurtherAwayThanDist_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,0,0,5,0,0,5,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 2),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 2),
                new Point2D.Double(0, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC6()).isEqualTo(false);
    }

    /**
     * LIC6 Test:
     * Two points are given, which is less than the required amount of points.
     * LIC6 is expected to return false.
     */
    @Test
    @DisplayName("LIC6: unsatisfied amount of numpoints returns false")
    void LIC6_NumpointsLessThanThree_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,0,0,5,0,0,3,0,0,0,0,0,0,0,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(10, 10)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC6()).isEqualTo(false);
    }

    /**
     * LIC7 Test:
     * Three points are given.
     * The first and third point are separated by 10.1 units.
     * Parameters LENGTH1 is set to 10 and K_PTS is set to 1.
     * LIC7 is expected to return true.
     */
	@Test
	@DisplayName("LIC7: satisfied returns true")
	void LIC7_GeneralCase_ReturnsTrue() {
		var points = new Point2D.Double[] {
				new Point2D.Double(0, 0),
				new Point2D.Double(10, 0),
				new Point2D.Double(10.1, 0)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				10,0,0,0,0, 0,0,0,0,
				0,0,1,0,0,0,0,0,0,0
		));
		Assertions.assertThat(cmv.LIC7()).isTrue();
	}

    /**
     * LIC7 Test:
     * Three points are given.
     * The first and third point are separated by 10 units.
     * Parameters LENGTH1 is set to 10 and K_PTS is set to 1.
     * LIC7 is expected to return false.
     */
	@Test
	@DisplayName("LIC7: unsatisfied returns false")
	void LIC7_GeneralCase_ReturnsFalse() {
		var points = new Point2D.Double[] {
				new Point2D.Double(0, 0),
				new Point2D.Double(10.1, 0),
				new Point2D.Double(10, 0)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				10,0,0,0,0, 0,0,0,0,
				0,0,1,0,0,0,0,0,0,0
		));
		Assertions.assertThat(cmv.LIC7()).isFalse();
	}

    /**
     * LIC8 Test:
     * Ten Points are given.
     * The first, fifth and eight points form circle with a radius of 12.75 units.
     * Parameters RADIUS1 is set to 4, A_PTS to 3, and B_PTS to 2.
     * LIC8 is expected to return true.
     */
	@Test
	@DisplayName("LIC8: satisfied condition returns true")
	void LIC8_SeparatedPointsOutsideOfRadius_ReturnsTrue() {
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(-6, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(-3, 2),
				new Point2D.Double(0, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
		};

		Parameters PARAMETERS = new Parameters(0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0);
		CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

		Assertions.assertThat(cmv.LIC8()).isTrue();
	}

    /**
     * LIC8 Test:
     * Ten Points are given.
     * The first, fifth and eight points form circle with a radius of 12.75 units.
     * Parameters RADIUS1 is set to 13, A_PTS to 3, and B_PTS to 2.
     * LIC8 is expected to return false.
     */
	@Test
	@DisplayName("LIC8: unsatisfied condition returns false")
	void LIC8_SeparatedPointsInsideOfRadius_ReturnsFalse() {
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(-6, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(-3, 2),
				new Point2D.Double(0, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
		};

		Parameters PARAMETERS = new Parameters(0, 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0);
		CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

		Assertions.assertThat(cmv.LIC8()).isFalse();
	}

    /**
     * LIC9  Test:
     * Five points are given.
     * The first, third, and fifth which forms an angle below PI/2.
     * Parameters EPSILON is set to PI/2, C_PTS to 1, and D_PTS to 1.
     * LIC9 is expected to return true.
     */
	@Test
    @DisplayName("LIC9: satisfied general-case")
    void LIC9_GeneralCase_ReturnsTrue() {
        var points = new Point2D.Double[]{
                new Point2D.Double(1, 0.001),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 1),
                new Point2D.Double(3, 1)
        };
        CMV cmv = new CMV(
                points.length, points, new Parameters(
                0, 0, 0, 0, Math.PI / 2, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0
        )
        );
        Assertions.assertThat(cmv.LIC9()).isTrue();
    }

    /**
     * LIC9 Test:
     * Five points are given.
     * The first, third, and fifth which forms an angle larger than PI/2 but smaller than 3PI/2.
     * Parameters EPSILON is set to PI/2, C_PTS to 1, and D_PTS to 1.
     * LIC9 is expected to return false.
     */
    @Test
    @DisplayName("LIC9: unsatisfied general-case")
    void LIC9_GeneralCase_ReturnsFalse() {
        var points = new Point2D.Double[]{
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(-1, 3),
                new Point2D.Double(-100, 1),
                new Point2D.Double(-200, 1)
        };
        CMV cmv = new CMV(
                points.length, points, new Parameters(
                0, 0, 0, 0, Math.PI / 2, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 1, 1, 0, 0, 0
        )
        );
        Assertions.assertThat(cmv.LIC9()).isFalse();
    }

    /**
     * LIC10 Test:  
     * Eight points are given.
     * The first, fourth, and seventh points form a triangle with an area of 2 units.
     * Parameters AREA1 is set to 1.99, E_PTS to 2, and F_PTS to 3.
     * LIC10 is expected to return true.
     */
	@Test
    @DisplayName("LIC10: satisfied condition returns true")
    void LIC10_SeparatedDataPointsHasTriangularAreaGreaterThanArea1_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,1.99,0,0,0,0,0,0,0,0,0,0,2,3,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(5, 2),
                new Point2D.Double(2, 8),
                new Point2D.Double(4, 0),
                new Point2D.Double(-2, -6),
                new Point2D.Double(-2, 2),
                new Point2D.Double(-2, 5),
                new Point2D.Double(2, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC10()).isEqualTo(true);
    }

    /**
     * LIC10 Test:  
     * Eight points are given.
     * The first, fourth, and seventh points form a triangle with an area of 2 units.
     * No other combinations of points separated by E_PTS and F_PTS exists. 
     * Parameters AREA1 is set to 1.99, E_PTS to 3, and F_PTS to 2.
     * LIC10 is expected to return false.
     */
    @Test
    @DisplayName("LIC10: unsatisfied condition returns false")
    void LIC10_NoSeparatedDataPointsHasTriangularAreaGreaterThanArea1_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,3,2,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(5, 2),
                new Point2D.Double(-2, 5),
                new Point2D.Double(2, 8),
                new Point2D.Double(4, 0),
                new Point2D.Double(-2, -6),
                new Point2D.Double(-2, 2),
                new Point2D.Double(2, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC10()).isEqualTo(false);
    }

    /**
     * LIC10 Test:
     * Four points are given.
     * Parameters E_PTS to 1 and F_PTS to 1.
     * LIC10 is expected to return false.
     */
    @Test
    @DisplayName("LIC10: unsatisfied amount of numpoints returns false")
    void LIC10_NumpointsLessThanFive_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,1,1,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(2, 2),
                new Point2D.Double(1, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC10()).isEqualTo(false);
    }

    /**
     * LIC11 Test:
     * Three points are given.
     * The first point is 1 unit ahead of the third on the x-axis.
     * Parameter G_PTS is set to 1.
     * LIC11 is expected to return true.
     */
    @Test
	@DisplayName("LIC11: satisfied returns true")
	void LIC11_Simple_ReturnsTrue() {
		var points = new Point2D.Double[] {
				new Point2D.Double(1, 0),
				new Point2D.Double(2, 0),
				new Point2D.Double(0, 0)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				0,0,0,0,0, 0.49,0,0,0,
				0,0,0,0,0,0,0,0,0,1
		));
		Assertions.assertThat(cmv.LIC11()).isTrue();
	}

    /**
     * LIC11 Test:
     * Three points are given.
     * The first point is equal to the third.
     * Parameter G_PTS is set to 1.
     * LIC11 is expected to return false.
     */
	@Test
	@DisplayName("LIC11: unsatisfied returns false")
	void LIC11_Simple_ReturnsFalse() {
		var points = new Point2D.Double[] {
				new Point2D.Double(0, 0),
				new Point2D.Double(-1, 0),
				new Point2D.Double(0, 0)
		};
		CMV cmv = new CMV(points.length, points, new Parameters(
				0,0,0,0,0, 0.5,0,0,0,
				0,0,0,0,0,0,0,0,0,1
		));
		Assertions.assertThat(cmv.LIC11()).isFalse();
	}

    /**
     * LIC12 Test:
     * Four points are given.
     * The first and third points are separated by 4 units. 
     * The second and fourth points are separated by more than 8.4 units.
     * Parameters LENGTH1 is set to 7 and LENGTH2 is set to 5.
     * LIC12 is expected to return true.
     */
	@Test
	@DisplayName("LIC12: satisfied condition returns true")
	void LIC12_BothConditionsTrue_ReturnsTrue()
	{
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(0,4),
				new Point2D.Double(0,0),
				new Point2D.Double(0, 8),
				new Point2D.Double(6,6),
		};

		Parameters PARAMETERS = new Parameters(7, 5, 0, 0, 0, 0, 0, 0, 0,02, 0, 1, 0, 0, 0, 0, 0, 0, 0);
		CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

		Assertions.assertThat(cmv.LIC12()).isTrue();
	}

     /**
     * LIC12 Test:
     * Four points are given.
     * The first and third points are separated by 4 units. 
     * The second and fourth points are separated by more than 8.4 units.
     * Parameters LENGTH1 is set to 10 and LENGTH2 is set to 5.
     * LIC12 is expected to return false.
     */
	@Test
	@DisplayName("LIC12: unsatisfied condition returns false")
	void LIC12_FirstConditionsFalse_ReturnsFalse()
	{
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(0,4),
				new Point2D.Double(0,0),
				new Point2D.Double(0, 8),
				new Point2D.Double(6,6),
		};

		Parameters PARAMETERS = new Parameters(10, 5, 0, 0, 0, 0, 0, 0, 0,02, 0, 1, 0, 0, 0, 0, 0, 0, 0);
		CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

		Assertions.assertThat(cmv.LIC12()).isFalse();
	}

    /**
     * LIC13 Test:
     * Six points are given. 
     * The first, fourth and sixth points form a circle with a radius of 1.58 unit.
     * Parameters RADIUS1 is set to 1, RADIUS2 is set to 2, A_PTS is set to 2, and B_PTS is set to 1.
     * LIC13 is expected to return true.
     */
    @Test
    @DisplayName("LIC13: both conditions satisfied returns true")
    void LIC13_threePointsCannotFitInCircleWithRADIUS1butRADIUS2_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0, 0, 1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(111, 111),
                new Point2D.Double(222, 222),
                new Point2D.Double(1, 1),
                new Point2D.Double(333, 333),
                new Point2D.Double(-2, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC13()).isTrue();
    }

    /**
     * LIC13 Test:
     * Six points are given. 
     * The first, fourth and sixth points form a circle with a radius of 1.58 unit.
     * Parameters RADIUS1 is set to 10, RADIUS2 is set to 2, A_PTS is set to 2, and B_PTS is set to 1.
     * LIC13 is expected to return false.
     */
    @Test
    @DisplayName("LIC13: unsatisfied first condition returns false")
    void LIC13_threePointsCanFitInCircleWithRADIUS1_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0, 0, 10, 2, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(111, 111),
                new Point2D.Double(222, 222),
                new Point2D.Double(1, 1),
                new Point2D.Double(333, 333),
                new Point2D.Double(-2, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC13()).isFalse();
    }

    /**
    * LIC13 Test:
    * Six points are given. 
    * The first, fourth and sixth points form a circle with a radius of 1.58 unit.
    * Parameters RADIUS1 is set to 10, RADIUS2 is set to 1, A_PTS is set to 2, and B_PTS is set to 1.
    * LIC13 is expected to return false.
    */
    @Test
    @DisplayName("LIC13: unsatisfied second condition returns false")
    void LIC13_threePointsCannotFitInCircleWithRADIUS2_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0, 0, 10, 1, 1, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(111, 111),
                new Point2D.Double(222, 222),
                new Point2D.Double(1, 1),
                new Point2D.Double(333, 333),
                new Point2D.Double(-2, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC13()).isFalse();
    }

    /**
     * LIC14 Test:
     * Nine points are given.
     * The first, fourth, and seventh points form a triangle with an area of 2 units.
     * The second, fifth, and eighth points form a triangle with an area of 0.5 units.
     * Parameters AREA1 is set to 1.99, AREA2 is set to 0.51, E_PTS is set to 2, and F_PTS is set to 3.
     * LIC14 is expected to return true.
     */
    @Test
    @DisplayName("LIC14: satisfied conditions returns true")
    void LIC14_SeparatedDataPointsHasTriangularAreaGreaterThanArea1AndLesserThanArea2_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,1.99,0.51,0,0,0,0,0,0,0,0,0,2,3,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(-1, -1),
                new Point2D.Double(2, 8),
                new Point2D.Double(4, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(-2, 2),
                new Point2D.Double(-2, 5),
                new Point2D.Double(2, 1),
                new Point2D.Double(-1, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC14()).isEqualTo(true);
    }

    /**
     * LIC14 Test:
     * Nine points are given.
     * The first, fourth, and seventh points form a triangle with an area of 2 units.
     * The second, fifth, and eighth points form a triangle with an area of 0.5 units.
     * Parameters AREA1 is set to 2.01, AREA2 is set to 0.51, E_PTS is set to 2, and F_PTS is set to 3.
     * LIC14 is expected to return true.
     */
    @Test
    @DisplayName("LIC14: unsatisfied first condition returns false")
    void LIC14_SeparatedDataPointsHasTriangularAreaNotGreaterThanArea1AndLesserThanArea2_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,2.01,0.51,0,0,0,0,0,0,0,0,0,2,3,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(-1, -1),
                new Point2D.Double(2, 8),
                new Point2D.Double(4, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(-2, 2),
                new Point2D.Double(-2, 5),
                new Point2D.Double(2, 1),
                new Point2D.Double(-1, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC14()).isEqualTo(false);
    }

    /**
     * LIC14 Test:
     * Nine points are given.
     * The first, fourth, and seventh points form a triangle with an area of 2 units.
     * The second, fifth, and eighth points form a triangle with an area of 0.5 units.
     * Parameters AREA1 is set to 1.99, AREA2 is set to 0.49, E_PTS is set to 2, and F_PTS is set to 3.
     * LIC14 is expected to return true.
     */
    @Test
    @DisplayName("LIC14: unsatisfied second condition returns false")
    void LIC14_SeparatedDataPointsHasTriangularAreaGreaterThanArea1AndNotLesserThanArea2_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,1.99,0.49,0,0,0,0,0,0,0,0,0,2,3,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(0, 0),
                new Point2D.Double(-1, -1),
                new Point2D.Double(2, 8),
                new Point2D.Double(4, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(-2, 2),
                new Point2D.Double(-2, 5),
                new Point2D.Double(2, 1),
                new Point2D.Double(-1, 0)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC14()).isEqualTo(false);
    }

    /**
     * LIC14 Test:
     * Four points are given.
     * Parameters E_PTS to 1 and F_PTS to 1.
     * LIC14 is expected to return false.
     */
    @Test
    @DisplayName("LIC14: unsatisfied amount of numpoints returns false")
    void LIC14_NumpointsLessThanFive_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0,0,0,0,0,3,0,0,0,0,0,0,0,0,0,0,1,1,0);
        Point2D.Double[] POINTS = {
                new Point2D.Double(1, 1),
                new Point2D.Double(0, 0),
                new Point2D.Double(2, 2),
                new Point2D.Double(1, 1)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        Assertions.assertThat(cmv.LIC14()).isEqualTo(false);
    }

}
