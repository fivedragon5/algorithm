package algorithm.code.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem2300 {

    /**
        1 ≤ N ≤ 10,000
        -1,000,000 <= x, y <= 1,000,000
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[][] maps = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            maps[i][0] = Integer.parseInt(st.nextToken());
            maps[i][1] = Integer.parseInt(st.nextToken());
        }



    }
}

/**
 3
 3 5
 1 3
 2 4
 */
