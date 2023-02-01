package algorithm.code.baekjoon.soon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 접근)
 *  1. 탐색, 구현
 *
 * 풀이)
 *
 * 제한)
 * 1 <= K <= 30
 * 1 <= W, H <= 200
 */
class Problem1600 {

    static int[] dxHorse = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[] dyHorse = {1, 2, 2, 1, -1, -2, -2, -1};
    static int[] dxMonkey = {-1, 0, 1, 0};
    static int[] dyMonkey = {0, 1, 0, -1};

    static int W,H,K;
    static int[][] map;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        move(0, 0, K, 0, map);

    }

    static void move(int currentX, int currentY, int horseMove, int moveCount, int[][] copyMap) {

        if (horseMove > 0) {

        }

    }
}
/*
1
4 4
0 0 0 0
1 0 0 0
0 0 1 0
0 1 0 0

4

2
5 2
0 0 1 1 0
0 0 1 1 0

-1
 */

