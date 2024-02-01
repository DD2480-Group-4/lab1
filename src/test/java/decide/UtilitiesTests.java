package decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;
import java.awt.geom.Point2D;

public class UtilitiesTests {

    @Test
    @DisplayName("calculateTriangleArea: Integer Vertices")
    void calculateTriangleArea_IntegerVertices_CalculatesAreaCorrect()
    {
        Point2D.Double p1 = new Point2D.Double(1, 1);
        Point2D.Double p2 = new Point2D.Double(-1, 1);
        Point2D.Double p3 = new Point2D.Double(1, -1);

        double area = Utilities.calculateTriangleArea(p1, p2, p3);

        Assertions.assertThat(area).isEqualTo(2);
    }

    @Test
    @DisplayName("calculateTriangleArea: Decimal Vertices")
    void calculateTriangleArea_DecimalVertices_CalculatesAreaCorrect()
    {
        Point2D.Double p1 = new Point2D.Double(1.5, 1.55);
        Point2D.Double p2 = new Point2D.Double(-1.5, 1.51);
        Point2D.Double p3 = new Point2D.Double(1.5, -1.3);

        double area = Utilities.calculateTriangleArea(p1, p2, p3);

        Assertions.assertThat(area).isEqualTo(4.275);
    }

    @Test
    @DisplayName("getQuadrant success")
    void getQuadrant_CalculatesQuadrantNumberCorrect() {
        Point2D.Double[] p = {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(1, 0),
                new Point2D.Double(0.3, 4),
                new Point2D.Double(-1, 0),
                new Point2D.Double(-0.3, 4),
                new Point2D.Double(0, -1),
                new Point2D.Double(-0.3, -4),
                new Point2D.Double(0.3, -4)};

        int[] expectedQuadrants = {1, 1, 1, 1, 2, 2, 3, 3, 4};
        for (int i = 0; i < p.length; i++) {
            Assertions.assertThat(Utilities.getQuadrant(p[i])).isEqualTo(expectedQuadrants[i]);
        }
    }

    @Test
    @DisplayName("getQuadrant fail")
    void getQuadrant_CalculatesQuadrantNumberIncorrect() {
        Point2D.Double[] p = {
                new Point2D.Double(-13, -27),
                new Point2D.Double(13, -27),
                new Point2D.Double(-13, 27),
                new Point2D.Double(13, 27)};

        int[] expectedQuadrants = {1, 2, 3, 4};
        for (int i = 0; i < p.length; i++) {
            Assertions.assertThat(Utilities.getQuadrant(p[i])).isNotEqualTo(expectedQuadrants[i]);
        }
    }

    @Test
    @DisplayName("checkPointsForTriangle: Correct Results")
    void checkPointsForTriangle_CorrectResult() {
        Point2D.Double[] points = {
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(1, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 0),
                new Point2D.Double(0, 1),
                new Point2D.Double(0, 0.1)
        };
        //Checks point 0, 2, and 9.
        Assertions.assertThat(
                Utilities.checkPointsForTriangle(
                        points, points.length, 1, 6, area -> area == 0.5
                )
        ).isTrue();
        //Checks point 1, 3 and 9.
        Assertions.assertThat(
                Utilities.checkPointsForTriangle(
                        points, points.length, 1, 5, area -> area == 0.5
                )
        ).isTrue();

        //Checks point 0, 2 and 10.
        Assertions.assertThat(
                Utilities.checkPointsForTriangle(
                        points, points.length, 1, 7, area -> area == 0.05
                )
        ).isTrue();

        //Check point 4, 7 and 10.
        Assertions.assertThat(
                Utilities.checkPointsForTriangle(
                        points, points.length, 2, 2, area -> area == 0.05
                )
        ).isTrue();

        //No sequence with only 1-wide gaps.
        Assertions.assertThat(
                Utilities.checkPointsForTriangle(
                        points, points.length, 1, 1, area -> area > 0
                )
        ).isFalse();
    }
}
