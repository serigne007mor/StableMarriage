package StableMarriage;

import java.util.Arrays;

public class StableMarriage {

    static final int maxMarriage = 4;

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int prefer[][] = new int[][] { { 7, 5, 6, 4 }, { 5, 4, 6, 7 }, { 4, 5, 6, 7 }, { 4, 5, 6, 7 }, { 0, 1, 2, 3 },
                { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
        stableMarriage(prefer);
    }

    static boolean wPrefersM1OverM(int preference[][], int currentWomen, int currentMen, int currentAssignment) {
        for (int i = 0; i < maxMarriage; i++) {
            if (preference[currentWomen][i] == currentAssignment)
                return true;

            if (preference[currentWomen][i] == currentMen)
                return false;

        }
        return false;
    }

    static void stableMarriage(int preference[][]) {
        int currentWomenProposal[] = new int[maxMarriage];

        boolean freeMen[] = new boolean[maxMarriage];

        Arrays.fill(currentWomenProposal, -1);
        int freeCount = maxMarriage;

        while (freeCount > 0) {

            int currentMen;
            for (currentMen = 0; currentMen < maxMarriage; currentMen++)
                if (freeMen[currentMen] == false)
                    break;

            for (int i = 0; i < maxMarriage && freeMen[currentMen] == false; i++) {
                int w = preference[currentMen][i];
                if (currentWomenProposal[w - maxMarriage] == -1) {
                    currentWomenProposal[w - maxMarriage] = currentMen;
                    freeMen[currentMen] = true;
                    freeCount--;
                } else {
                    int m1 = currentWomenProposal[w - maxMarriage];
                    if (wPrefersM1OverM(preference, w, currentMen, m1) == false) {
                        currentWomenProposal[w - maxMarriage] = currentMen;
                        freeMen[currentMen] = true;
                        freeMen[m1] = false;
                    }
                }
            }
        }

        System.out.println("Woman Man");
        for (int i = 0; i < maxMarriage; i++) {
            System.out.print(" ");
            System.out.println(i + maxMarriage + "	 " + currentWomenProposal[i]);
        }
    }

}
