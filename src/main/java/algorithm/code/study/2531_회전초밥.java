package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 제한)
 *  2 ≤ N ≤ 30,000
 *  2 ≤ d ≤ 3,000
 *  2 ≤ k ≤ 3,000 (k ≤ N)
 *  1 ≤ c ≤ d
 */
class Problem2531 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] dish = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dish[i] = Integer.parseInt(st.nextToken());
        }
    }
}

/*
8 30 4 30
7
9
7
30
2
7
9
25
 */

