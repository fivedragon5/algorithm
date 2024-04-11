package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Problem14500 {

    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static int MAX = 0;
    static int N, M; //y, x
    static int[][] map;
    static boolean[][] vistied;

    /**
      4 ≤ N, M ≤ 500
      0 < number < 1000
     */

    public static void main(String args[]) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        vistied = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                vistied[i][j] = true;
                solve(j, i, 1, map[i][j]);
                vistied[i][j] = false;
            }
        }

        System.out.println(MAX);
    }

    static void solve(int x, int y, int step, int sum) {

        if (step == 4) {
            MAX = Math.max(MAX, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            
            // map을 벗어날경우 처리
            if (nextX < 0 || nextY < 0 || nextX >= M || nextY >= N) {
                continue;
            }

            if (!vistied[nextY][nextX]) {

                if (step == 2) {
                    vistied[nextY][nextX] = true;
                    solve(x, y, step + 1, sum + map[nextY][nextX]);
                    vistied[nextY][nextX] = false;
                }

                vistied[nextY][nextX] = true;
                solve(nextX, nextY, step + 1, sum + map[nextY][nextX]);
                vistied[nextY][nextX] = false;
            }
        }
    }
}

/**
 5 5
 1 2 3 4 5
 5 4 3 2 1
 2 3 4 5 6
 6 5 4 3 2
 1 2 1 2 1
 19

 4 5
 1 2 3 4 5
 1 2 3 4 5
 1 2 3 4 5
 1 2 3 4 5
 20

 4 10
 1 2 1 2 1 2 1 2 1 2
 2 1 2 1 2 1 2 1 2 1
 1 2 1 2 1 2 1 2 1 2
 2 1 2 1 2 1 2 1 2 1
 7

 */
