package decide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.awt.geom.Point2D;

public class CMVTests {

    @Test
    @DisplayName("LIC0: satisfied condition returns true")
    void LIC0_ConsecutiveDataPointsFurtherThanLength_ReturnsTrue()
    {
        Parameters PARAMETERS = new Parameters(3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {new Point2D.Double(0, 0), new Point2D.Double(0, 4), new Point2D.Double(0, 8)};

        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC0()).isTrue();
    }

    @Test
    @DisplayName("LIC0: satisfied condition returns false")
    void LIC0_ConsecutiveDataPointsCloserThanLength_ReturnsFalse()
    {
        Parameters PARAMETERS = new Parameters(5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        Point2D.Double[] POINTS = new Point2D.Double[] {new Point2D.Double(0, 0), new Point2D.Double(0, 4), new Point2D.Double(0, 8)};

        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

        Assertions.assertThat(cmv.LIC0()).isFalse();
    }

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

    @Test
    @DisplayName("LIC4: satisfied condition returns false")
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

	@Test
	@DisplayName("LIC8: satisfied condition returns true")
	void LIC8_SeparatedPointsOutsideOfRadius_ReturnsTrue() {
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(-6, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(-3, 2),
				new Point2D.Double(0, 0),
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

	@Test
	@DisplayName("LIC8: satisfied condition returns false")
	void LIC8_SeparatedPointsInsideOfRadius_ReturnsFalse() {
		Point2D.Double[] POINTS = new Point2D.Double[] {
				new Point2D.Double(-6, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(-3, 2),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 3),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
				new Point2D.Double(0, 0),
		};

		Parameters PARAMETERS = new Parameters(0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 2, 0, 0, 0, 0, 0);
		CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);

		Assertions.assertThat(cmv.LIC8()).isFalse();
	}

	@Test
    @DisplayName("LIC9: satisfied general-case")
    void LIC9_GeneralCase_ReturnsTrue() {
        var points = new Point2D.Double[]{
                new Point2D.Double(1, 0.001),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 1)
        };
        CMV cmv = new CMV(
                points.length, points, new Parameters(
                0, 0, 0, 0, Math.PI / 2, 0, 0, 0, 0,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        )
        );
        Assertions.assertThat(cmv.LIC9()).isTrue();
    }

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
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0
        )
        );
        Assertions.assertThat(cmv.LIC9()).isFalse();
    }

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
