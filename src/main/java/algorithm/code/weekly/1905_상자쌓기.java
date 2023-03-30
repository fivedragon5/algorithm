package algorithm.code.weekly;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        // lx, ly, lz, px, py
        // 가로, 세로, 높이,

        int[][] array = new int[1000][1000];

        int max = 0;

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken()) - 1;
            int dx = Integer.parseInt(st.nextToken()) - 1;
            int dy = Integer.parseInt(st.nextToken()) - 1;

            for (int j = dx; j < dx + x; j++) {

            }

        }
    }
}
/*
7 5 4
4 3 2 0 0
3 3 1 3 0
7 1 2 0 3
2 3 3 2 2
*/
