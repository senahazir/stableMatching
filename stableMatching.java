import java.util.*;

public class stableMatching {
    static int N;

    static boolean cLikesJ1moreJ(int prefList[][], int c, int j, int j1) {
        for (int i = 0; i < N; i++) {
            if (prefList[c][i] == j1) {
                return true;
            }
            if (prefList[c][i] == j) {
                return false;
            }
        }
        return false;
    }

    static void stableMarriage(int[][] pref1, int[][]pref2, int size) {
        int[][] combined = new int[size*2][size];
        for (int i = 0; i < size; i ++) {
            combined[i] = pref1[i];
        }
        for (int i = 0; i < size; i ++) {
            combined[i + size] = pref2[i];
        }

        N = size;
        int cMatch[] = new int[N];
        boolean jFilled[] = new boolean[N];
        Arrays.fill(cMatch, -1);
        int freeCount = N;

        while (freeCount > 0) {
            int j;
            for (j = 0; j < N; j++) {
                if (jFilled[j] == false) {
                    break;
                }
            }
                for (int i = 0; i < N && !jFilled[j]; i++) {
                    int c = combined[j][i];
                    if (cMatch[c-N] == -1) {
                        cMatch[c-N] = j;
                        jFilled[j] = true;
                        freeCount--;
                    }
                    else{
                        int j1 = cMatch[c-N];
                        if (!cLikesJ1moreJ(combined, c, j, j1)) {
                            jFilled[j] = true;
                            jFilled[j1] = false;
                        }
                    }
                }

            }

        System.out.println("Candidate Job");
        for (int i = 0; i < N; i++)
        {
            System.out.print(" ");
            System.out.println(i + N + "     " +
                    cMatch[i]);
        }
    }



    // Driver Code
    public static void main(String[] args)
    {
        int[][] pref1 = new int[][]{{7, 5, 6, 4},
                {6, 4, 5, 7},
                {5, 4, 6, 7},
                {4, 5, 6, 7}};
        int[][] pref2 = new int[][]{
                {3, 1, 2, 0},
                {2, 1, 0, 3},
                {1, 0, 2, 3},
                {0, 1, 2, 3}
        };

        stableMarriage(pref1, pref2, pref1[0].length);
    }
}
