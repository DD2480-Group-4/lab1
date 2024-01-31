package decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import java.util.Arrays;

public class LaunchTests {

    @Test
    @DisplayName("PUM generation: test NOTUSED")
    void calcPUM_LCMFilledWithNOTUSED_ReturnsPUMWithAllTrue() {
        LCMOperators[][] lcm = {
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED}};

        boolean[] cmv = {false, false, false};

        boolean[][] keyPUM = {
                {false, true, true},
                {true, false, true},
                {true, true, false}};

        boolean[][] pum = Launch.calcPUM(lcm, cmv);
        // E.g. NOTUSED always yield "true" in PUM
        Assertions.assertThat(pum).isEqualTo(keyPUM);
    }

    @Test
    @DisplayName("PUM generation: test from lab description")
    void calcPUM_ShouldPass() {
        LCMOperators[][] lcm = {
                {LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.NOTUSED},
                {LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ORR, LCMOperators.NOTUSED},
                {LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.NOTUSED},
                {LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.NOTUSED},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED}};

        boolean[] cmv = {false, true, true, true, false};

        boolean[][] keyPUM = {
                {false, false, true, false, true},
                {false, false, true, true, true},
                {true, true, false, true, true},
                {false, true, true, false, true},
                {true, true, true, true, false}};

        boolean[][] pum = Launch.calcPUM(lcm, cmv);
        // E.g. (From lab description) PUM[0,1] is false because LCM[0,1] is ANDD, and at least one of CMV[0] and CMV[1] is false...
        Assertions.assertThat(pum).isEqualTo(keyPUM);
    }

    @Test
    @DisplayName("PUM generation: incorrect PUM")
    void calcPUM_ShouldFail() {
        LCMOperators[][] lcm = {
                {LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.NOTUSED},
                {LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.ORR},
                {LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.NOTUSED}};

        boolean[] cmv = {false, false, true};

        boolean[][] keyPUM = {
                {false, true, true},
                {true, false, false},
                {true, true, false}};

        boolean[][] pum = Launch.calcPUM(lcm, cmv);
        // E.g. lcm[0][1] (ORR) should together with (cmv[0] || cmv[1]) yield "false", which is not equal to "true" in keyPUM
        Assertions.assertThat(pum).isNotEqualTo(keyPUM);
    }

}
