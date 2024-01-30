package decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
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

}
