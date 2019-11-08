package StableMarriage;

import java.util.Arrays;

public class StableMarriage {

    // static final int maxMarriage = 4;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int prefer[][] = new int[][] { { 7, 5, 6, 4 }, { 5, 4, 6, 7 }, { 4, 5, 6, 7 }, { 4, 5, 6, 7 }, { 0, 1, 2, 3 },
                { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
        stableMarriage(prefer);
    }

    static boolean wPrefersM1OverM(int preference[][], int currentWomen, int currentMen, int currentAssignment) {
        for (int i = 0; i < preference[0].length; i++) {
            if (preference[currentWomen][i] == currentAssignment)
                return true;

            if (preference[currentWomen][i] == currentMen)
                return false;

        }
        return false;
    }

    static void stableMarriage(int preference[][]) {
        int currentWomenProposal[] = new int[preference[0].length];

        boolean freeMen[] = new boolean[preference[0].length];

        Arrays.fill(currentWomenProposal, -1);
        int freeCount = preference[0].length;

        while (freeCount > 0) {

            int currentMen;
            for (currentMen = 0; currentMen < preference[0].length; currentMen++)
                if (freeMen[currentMen] == false)
                    break;

            for (int i = 0; i < preference[0].length && freeMen[currentMen] == false; i++) {
                int w = preference[currentMen][i];
                if (currentWomenProposal[w - preference[0].length] == -1) {
                    currentWomenProposal[w - preference[0].length] = currentMen;
                    freeMen[currentMen] = true;
                    freeCount--;
                } else {
                    int m1 = currentWomenProposal[w - preference[0].length];
                    if (wPrefersM1OverM(preference, w, currentMen, m1) == false) {
                        currentWomenProposal[w - preference[0].length] = currentMen;
                        freeMen[currentMen] = true;
                        freeMen[m1] = false;
                    }
                }
            }
        }

        System.out.println("Woman   Man");
        for (int i = 0; i < preference[0].length; i++) {
            System.out.print(" ");
            System.out.println(i + preference[0].length + "	 " + currentWomenProposal[i]);
        }
    }

}
