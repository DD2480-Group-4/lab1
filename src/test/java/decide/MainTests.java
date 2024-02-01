package decide;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.geom.Point2D;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTests {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private final ByteArrayOutputStream err = new ByteArrayOutputStream();
    private final PrintStream origOut = System.out;
    private final PrintStream origErr = System.err;

    @BeforeEach
    void setUpStream() {
        System.setOut(new PrintStream(out));
        System.setErr(new PrintStream(err));
    }

    @AfterEach
    void closeStream() {
        System.setOut(origOut);
        System.setErr(origErr);
    }

    /**
     * DECIDE Test:
     * Seven points in conjunction with 19 parameters yield "true" for all LICs.
     * Custom LCM creates PUM, which together with PUV results in all-true FUV.
     * DECIDE is expected to print "YES" to standard output.
     */
    @Test
    @DisplayName("DECIDE: launch-unlock signal generated")
    void DECIDE_FUVAllTrue_Launch() {
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
        LCMOperators[][] lcm = {
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
        boolean[] puv = {
                true, true, false, true, true,
                true, false, true, false, false,
                true, true, false, true, true};

        // Run intermediate steps
        CMV CMV = new CMV(POINTS.length, POINTS, PARAMETERS);
        boolean[] cmv = CMV.calcCMV();
        boolean[][] pum = Launch.calcPUM(lcm, cmv);
        boolean[] fuv = Launch.calcFUV(pum, puv);

        // Verify that input yields expected CMV, PUM and FUV
        boolean[] keyCMV = {
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true};
        boolean[][] keyPUM = {
                {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, false, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, false, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, false, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, false, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, false, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, false, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true, false, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, true, true, false, true, true, true, true, true},
                {true, true, true, true, true, true, true, true, true, true, false, true, true, true, true},
                {true, true, true, true, true, true, true, true, true, true, true, false, true, true, true},
                {true, true, true, true, true, true, true, true, true, true, true, true, false, true, true},
                {true, true, true, true, true, true, true, true, true, true, true, true, true, false, true},
                {true, true, true, true, true, true, true, true, true, true, true, true, true, true, false}};
        boolean[] keyFUV = {
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, true, true, true};
        Assertions.assertThat(cmv).isEqualTo(keyCMV);
        Assertions.assertThat(pum).isEqualTo(keyPUM);
        Assertions.assertThat(fuv).isEqualTo(keyFUV);

        // Verify that launch-unlock signal is generated to standard output given that all FUV elements are true
        Main.DECIDE(POINTS.length, POINTS, PARAMETERS, lcm, puv);
        Assertions.assertThat(out.toString()).isEqualTo("YES\n");
    }

    /**
     * DECIDE Test:
     * Seven points in conjunction with 19 parameters yield "false" on LIC12 and LIC13.
     * Custom LCM creates PUM, which together with PUV results in FUV[7], FUV[10], FUV[11], FUV[13] and FUV[14] being false.
     * DECIDE is expected to print "NO" to standard output.
     */
    @Test
    @DisplayName("DECIDE: launch-unlock signal not generated due to FUV not true")
    void DECIDE_FUVNotAllTrue_NoLaunch() {
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
                2, 1, 1, 1, 0,
                2, 2, 0, 7, 1,
                3, 5, 1, 2, 1,
                2, 1, 2, 1);
        LCMOperators[][] lcm = {
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
        boolean[] puv = {
                true, true, false, true, true,
                true, false, true, false, false,
                true, true, false, true, true};

        // Run intermediate steps
        CMV CMV = new CMV(POINTS.length, POINTS, PARAMETERS);
        boolean[] cmv = CMV.calcCMV();
        boolean[][] pum = Launch.calcPUM(lcm, cmv);
        boolean[] fuv = Launch.calcFUV(pum, puv);

        // Verify that input yields expected CMV, PUM and FUV
        boolean[] keyCMV = {
                true, true, true, true, true,
                true, true, true, true, true,
                true, true, false, false, true};
        boolean[][] keyPUM = {
                {false, true, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, false, true, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, false, true, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, false, true, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, false, true, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, false, true, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, false, true, true, true, true, true, true, true, true},
                {true, true, true, true, true, true, true, false, true, true, true, true, true, false, true},
                {true, true, true, true, true, true, true, true, false, true, true, true, false, true, true},
                {true, true, true, true, true, true, true, true, true, false, true, true, true, false, true},
                {true, true, true, true, true, true, true, true, true, true, false, true, true, false, true},
                {true, true, true, true, true, true, true, true, true, true, true, false, true, false, true},
                {true, true, true, true, true, true, true, true, false, true, true, true, false, false, true},
                {true, true, true, true, true, true, true, false, true, false, false, false, false, false, false},
                {true, true, true, true, true, true, true, true, true, true, true, true, true, false, false}};
        boolean[] keyFUV = {
                true, true, true, true, true,
                true, true, false, true, true,
                false, false, true, false, false};
        Assertions.assertThat(cmv).isEqualTo(keyCMV);
        Assertions.assertThat(pum).isEqualTo(keyPUM);
        Assertions.assertThat(fuv).isEqualTo(keyFUV);

        // Verify that launch-unlock signal is not generated to standard output given that FUV[7], FUV[10], FUV[11], FUV[13] and FUV[14] being false
        Main.DECIDE(POINTS.length, POINTS, PARAMETERS, lcm, puv);
        Assertions.assertThat(out.toString()).isEqualTo("NO\n");
    }

    /**
     * DECIDE Test:
     * No points, empty LCM and PUV alongside all-zero Parameters are passed as input.
     * DECIDE is expected to print error regarding faulty input dimensions.
     */
    @Test
    @DisplayName("DECIDE: launch-unlock signal not generated due to insufficient input")
    void DECIDE_InsufficientInput_NoLaunch() {
        Point2D.Double[] POINTS = {};
        Parameters PARAMETERS = new Parameters(
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0);
        LCMOperators[][] lcm = {};
        boolean[] puv = {};

        // Verify that error is printed due to insufficient input, and no launch-unlock signal is generated (empty output)
        Main.DECIDE(POINTS.length, POINTS, PARAMETERS, lcm, puv);
        Assertions.assertThat(out.toString()).isEqualTo("");
        Assertions.assertThat(err.toString()).isEqualTo(
                "ERROR: LCM does not follow the dimension specification (15x15). Found: (0x0).\n");
    }

}
