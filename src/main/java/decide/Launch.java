package decide;


public class Launch {

    public static boolean[][] calcPUM(LCMOperators[][] lcm, boolean[] cmv) {
        boolean[][] pum = new boolean[15][15];
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
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
