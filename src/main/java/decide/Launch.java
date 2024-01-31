package decide;


public class Launch {

    public static boolean[][] calcPUM(LCMOperators[][] lcm, boolean[] cmv) {
        boolean[][] pum = new boolean[lcm.length][lcm.length];
        for (int i = 0; i < lcm.length; i++) {
            for (int j = 0; j < lcm.length; j++) {

                if (i == j) {
                    continue;
                }
                if (lcm[i][j] == LCMOperators.ANDD) {
                    pum[i][j] = (cmv[i] && cmv[j]);
                } else if (lcm[i][j] == LCMOperators.ORR) {
                    pum[i][j] = (cmv[i] || cmv[j]);
                } else {
                    //NOTUSED
                    pum[i][j] = true;
                }
            }
        }
        return pum;
    }

    public static boolean[] calcFUV(boolean[][] pum, boolean[] puv) {
        return new boolean[15];
    }
    
}
