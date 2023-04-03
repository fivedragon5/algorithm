package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 1 ≤ Lx, Ly ≤ 1,000
 * 1 ≤ N ≤ 20,000
 * 1 ≤ lx
 * 0 ≤ px
 * px+lx ≤ Lx
 * 1 ≤ ly
 * 0 ≤ py
 * py+ly ≤ Ly
 * 1 ≤ lz ≤ 100,000
 */
class Problem1905 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Lx = Integer.parseInt(st.nextToken()); // 가로
        int Ly = Integer.parseInt(st.nextToken()); // 세로
        int N = Integer.parseInt(st.nextToken()); // 상자갯수

        int[][] layer = new int[Ly][Lx];

        int max = 0;
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            max = 0;

            for(int k = dy; k < dy + y; k++) {
                for (int j = dx; j < dx + x; j++) {
                    max = Math.max(max, layer[k][j] + z);
                }
            }
            for(int k = dy; k < dy + y; k++) {
                for (int j = dx; j < dx + x; j++) {
                    layer[k][j] += max;
                }
            }
            answer = Math.max(answer, max + z);
        }
        for (int[] a : layer) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println(answer);
    }
}
/*
7 5 4
4 3 2 0 0
3 3 1 3 0
7 1 2 0 3
2 3 3 2 2
*/
