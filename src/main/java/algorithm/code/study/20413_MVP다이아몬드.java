package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * 제한)
 * 1 ≤ N ≤ 36
 * 0 < s < g < p < d ≤ 500
 */
class Problem20413 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] gradeTable = new int[4];

        for (int i = 0; i < 4; i++) {
            gradeTable[i] = Integer.parseInt(st.nextToken(" "));
        }
        st = new StringTokenizer(br.readLine());

        String grades = st.nextToken();

        }
}
/*
3
30 60 90 150
BSG
 */

