package algorithm.code.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  1 ≤ N ≤ 10,000
 *  1 ≤ M ≤ 10
 */
class Problem23843 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int[] sockets = new int[N];

        for (int i = 0; i < N; i++) {
            sockets[i] = Integer.parseInt(st.nextToken());
        }
    }
}
/*
5 2
1 4 4 8 1

9
 */

