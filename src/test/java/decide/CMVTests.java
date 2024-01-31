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
    @DisplayName("LIC9: satisfied condition returns true")
    void LIC9_threeSeparatedDataPointsFormSatisfiedAngle_ReturnsTrue() {
        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0);
		Point2D.Double[] POINTS = {
                new Point2D.Double(0,0),
                new Point2D.Double(0,0),
                new Point2D.Double(0,0),
                new Point2D.Double(-10,0),
                new Point2D.Double(0,0),
                new Point2D.Double(0,10)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        // 45-degree angle should be larger than (PI + 1)
        Assertions.assertThat(cmv.LIC9()).isTrue();
    }

    @Test
    @DisplayName("LIC9: unsatisfied condition returns false")
    void LIC9_threeSeparatedDataPointsFormUnsatisfiedAngle_ReturnsFalse() {
        Parameters PARAMETERS = new Parameters(0, 0, 0, 0, 45, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 1, 0, 0, 0);
		Point2D.Double[] POINTS = {
                new Point2D.Double(0,0),
                new Point2D.Double(0,0),
                new Point2D.Double(0,0),
                new Point2D.Double(-10,0),
                new Point2D.Double(0,0),
                new Point2D.Double(0,10)};
        CMV cmv = new CMV(POINTS.length, POINTS, PARAMETERS);
        // 45-degree angle should not be larger than (PI + 45)
        Assertions.assertThat(cmv.LIC9()).isFalse();
    }
}
