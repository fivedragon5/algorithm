package algorithm.backjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Problem14466 {

    static int n, k, r;
    static int[] xMove = {1,0,-1,0};
    static int[] yMove = {0,1,0,-1};

    public static void main(String srgs[]) throws IOException {
        /** ex
         3 3 3
         2 2 2 3
         3 3 3 2
         3 3 2 3
         3 3
         2 2
         2 3
         */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] map = new int[n+1][n+1];
        int[][] visited;

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b =  Integer.parseInt(st.nextToken());
            int c =  Integer.parseInt(st.nextToken());
            int d =  Integer.parseInt(st.nextToken());
        }
    }
}
