package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1520
 *
 * 제한)
 *  1 ≤ N,M ≤ 500
 *  1 ≤ 지도 칸의 높이 ≤ 10,000
 *
 * 문제)
 *  1. N * M 크기의 지도가 있다.
 *  2. 지도의 각 칸에는 그 지점에 높이가 쓰여있고 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳 끼리만 가능하다
 *  3. 왼쪽 위에서 오른쪽 아래로 이동할때 항상 내리막길로만 이동하는 경로의 개수를 구하기
 *
 * 풀이)
 *  DFS + DP 메모제이션 O(N*M)
 *   - DP[i][j] : i,j에서 시작할때 내리막길로 이동해서 목표지점에 도달하는 경로의 수
 *   1. 0,0 에서 DFS 시작
 *   2. N-1,M-1에 도달하면 1을 리턴 (경로 하나 발견)
 *   3. DFS를 진행하면서 이미 계산된 (-1이 아닐 경우) 값이 있을경우 해당 값 반환 (메모제이션)
 *   4. 0,0 값 출력
 *
 */
class Problem1520 {

    private static int N;
    private static int M;
    private static int[][] MAP;
    private static int[] dx = new int[]{0, 1, 0, -1};
    private static int[] dy = new int[]{1, 0, -1, 0};

    public static void main(String args[]) throws IOException {
        input();

        int[][] dp = new int[N][M];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        dfs(dp, 0, 0);

        System.out.println(dp[0][0]);

    }

    private static int dfs(int[][] dp, int x, int y) {
        if (x == N-1 && y == M-1) {
            return 1;
        }

        if (dp[x][y] != -1) {
            return dp[x][y];
        }

        dp[x][y] = 0;

        for (int k = 0; k < 4; k++) {
            int moveX = x + dx[k];
            int moveY = y + dy[k];
            if (moveX >= 0 && moveY >= 0 && moveX < N && moveY < M) {
                if (MAP[x][y] > MAP[moveX][moveY]) {
                    dp[x][y] += dfs(dp, moveX, moveY);
                }
            }
        }
        return dp[x][y];
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        MAP = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                MAP[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
/*
4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10

3
 */

