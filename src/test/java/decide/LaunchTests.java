package decide;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

public class LaunchTests {

        /**
         * calcPUM Test:
         * A LCM matrix is filled with NOTUSED is given.
         * A CMV matrix with all false is given.
         * A PUM matrix with all values, except the diagonal, set to true is expected
         * from calcPUM.
         */
        @Test
        @DisplayName("PUM generation: test NOTUSED")
        void calcPUM_LCMFilledWithNOTUSED_ReturnsPUMWithAllTrue() {
                LCMOperators[][] lcm = {
                                { LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED },
                                { LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED },
                                { LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED } };

                boolean[] cmv = { false, false, false };

                boolean[][] keyPUM = {
                                { false, true, true },
                                { true, false, true },
                                { true, true, false } };

                boolean[][] pum = Launch.calcPUM(lcm, cmv);
                // E.g. NOTUSED always yield "true" in PUM
                Assertions.assertThat(pum).isEqualTo(keyPUM);
        }

        /**
         * calcPUM Test:
         * A LCM matrix with mixed ANDD, ORR, and NOTUSED operators is given.
         * A CMV matrix with mixed true and false is given.
         * A PUM matrix with values based on the LCM and CMV is expected from calcPUM.
         */
        @Test
        @DisplayName("PUM generation: test from lab description")
        void calcPUM_ShouldPass() {
                LCMOperators[][] lcm = {
                                { LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD,
                                                LCMOperators.NOTUSED },
                                { LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ORR,
                                                LCMOperators.NOTUSED },
                                { LCMOperators.ORR, LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD,
                                                LCMOperators.NOTUSED },
                                { LCMOperators.ANDD, LCMOperators.ORR, LCMOperators.ANDD, LCMOperators.ANDD,
                                                LCMOperators.NOTUSED },
                                { LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.NOTUSED,
                                                LCMOperators.NOTUSED, LCMOperators.NOTUSED } };

                boolean[] cmv = { false, true, true, true, false };

                boolean[][] keyPUM = {
                                { false, false, true, false, true },
                                { false, false, true, true, true },
                                { true, true, false, true, true },
                                { false, true, true, false, true },
                                { true, true, true, true, false } };

                boolean[][] pum = Launch.calcPUM(lcm, cmv);
                // E.g. (From lab description) PUM[0,1] is false because LCM[0,1] is ANDD, and
                // at least one of CMV[0] and CMV[1] is false...
                Assertions.assertThat(pum).isEqualTo(keyPUM);
        }

        /**
         * calcPUM Test:
         * A LCM matrix with mixed ANDD, ORR, and NOTUSED operators is given.
         * A CMV matrix with mixed true and false is given.
         * The PUM matrix from calcPUM is expected to not be one know to be incorrect. 
         */
        @Test
        @DisplayName("PUM generation: incorrect PUM")
        void calcPUM_ShouldFail() {
                LCMOperators[][] lcm = {
                                { LCMOperators.NOTUSED, LCMOperators.ORR, LCMOperators.NOTUSED },
                                { LCMOperators.NOTUSED, LCMOperators.NOTUSED, LCMOperators.ORR },
                                { LCMOperators.ANDD, LCMOperators.ANDD, LCMOperators.NOTUSED } };

                boolean[] cmv = { false, false, true };

                boolean[][] keyPUM = {
                                { false, true, true },
                                { true, false, false },
                                { true, true, false } };

                boolean[][] pum = Launch.calcPUM(lcm, cmv);
                // E.g. lcm[0][1] (ORR) should together with (cmv[0] || cmv[1]) yield "false",
                // which is not equal to "true" in keyPUM
                Assertions.assertThat(pum).isNotEqualTo(keyPUM);
        }

        /**
         * calcFUV Test:
         * A PUM matrix, and a PUV matrix are given, both with mixed true false values.
         * A FUV matrix with values based on the PUM and PUV is expected from calcFUV. 
         */
        @Test
        @DisplayName("FUV generation: test based on criteria in lab description")
        void calcFUV_ShouldPass() {

                boolean[][] pum = {
                                { false, true, true, true },
                                { true, false, false, false },
                                { true, true, true, true },
                                { true, true, false, false } };

                boolean[] puv = { true, false, true, true };

                boolean[] keyFUV = { true, true, true, false };

                boolean[] fuv = Launch.calcFUV(pum, puv);
                // E.g. fuv[0] is true because all values in pum[0][j] except j=0 are true
                // fuv[1] is true because puv[1] is false
                // fuv[2] is true based on the same logic as fuv[0],
                // but also shows that the values on the diagonal of pum are irrelevant
                // fuv[3] is false because not all values in pum[0][j] except j=0 are true
                Assertions.assertThat(fuv).isEqualTo(keyFUV);
        }
}
