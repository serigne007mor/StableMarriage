package StableMarriage;

import java.util.Arrays;

public class StableMarriage {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int preferenceList[][] = new int[][] { { 7, 5, 6, 4 }, { 5, 4, 6, 7 }, { 4, 5, 6, 7 }, { 4, 5, 6, 7 }, { 0, 1, 2, 3 },
                { 0, 1, 2, 3 }, { 0, 1, 2, 3 }, { 0, 1, 2, 3 } };
        stableMarriage(preferenceList);
    }

    static boolean wPrefersM1OverM(int preferenceList[][], int currentWomen, int currentMen, int currentAssignment) {
        for (int i = 0; i < preferenceList[0].length; i++) {
            if (preferenceList[currentWomen][i] == currentAssignment){
                return true;
            }

            if (preferenceList[currentWomen][i] == currentMen){
                return false;
            }

        }
        return false;
    }

    static void stableMarriage(int preferenceList[][]) {
        int currentWomenProposal[] = new int[preferenceList[0].length];

        boolean freeMen[] = new boolean[preferenceList[0].length];

        Arrays.fill(currentWomenProposal, -1);
        int freeCount = preferenceList[0].length;

        while (freeCount > 0) {

            int currentMen;
            for (currentMen = 0; currentMen < preferenceList[0].length; currentMen++)
                if (freeMen[currentMen] == false){
                    break;
                }

            for (int i = 0; i < preferenceList[0].length && freeMen[currentMen] == false; i++) {
                int w = preferenceList[currentMen][i];
                if (currentWomenProposal[w - preferenceList[0].length] == -1) {
                    currentWomenProposal[w - preferenceList[0].length] = currentMen;
                    freeMen[currentMen] = true;
                    freeCount--;
                } else {
                    int m1 = currentWomenProposal[w - preferenceList[0].length];
                    if (wPrefersM1OverM(preferenceList, w, currentMen, m1) == false) {
                        currentWomenProposal[w - preferenceList[0].length] = currentMen;
                        freeMen[currentMen] = true;
                        freeMen[m1] = false;
                    }
                }
            }
        }

        System.out.println("Woman   Man");
        for (int i = 0; i < preferenceList[0].length; i++) {
            System.out.print(" ");
            System.out.println(i + preferenceList[0].length + "	 " + currentWomenProposal[i]);
        }
    }

}
