package StableMarriage;

import java.util.Arrays;

public class StableMarriage {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int preferenceList[][] = new int[][] { {4,3,5}, {5,4,3}, {3,5,4}, {1,0,2},{2,1,0},{0,2,1}};
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
        int proposerArray[] = new int[preferenceList[0].length];

        boolean freeMen[] = new boolean[preferenceList[0].length];

        Arrays.fill(proposerArray, -1);
        int freeCount = preferenceList[0].length;

        while (freeCount > 0) {

            int currentMen;
            for (currentMen = 0; currentMen < preferenceList[0].length; currentMen++)
                if (freeMen[currentMen] == false){
                    break;
                }

            for (int i = 0; i < preferenceList[0].length && freeMen[currentMen] == false; i++) {
                int w = preferenceList[currentMen][i];
                if (proposerArray[w - preferenceList[0].length] == -1) {
                    proposerArray[w - preferenceList[0].length] = currentMen;
                    freeMen[currentMen] = true;
                    freeCount--;
                } else {
                    int currentMariage = proposerArray[w - preferenceList[0].length];
                    if (wPrefersM1OverM(preferenceList, w, currentMen, currentMariage) == false) {
                        proposerArray[w - preferenceList[0].length] = currentMen;
                        freeMen[currentMen] = true;
                        freeMen[currentMariage] = false;
                    }
                }
            }
        }

        System.out.println("man   Woman");
        for (int i = 0; i < preferenceList[0].length; i++) {
            System.out.print(" ");
            System.out.printf("man %d proposes to woman %d\n",proposerArray[i] ,i+preferenceList[0].length);
        }
    }

}
