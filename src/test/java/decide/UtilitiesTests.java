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

}
