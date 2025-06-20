package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1915
 *
 * 제한)
 *  1 ≤ n, m ≤ 1,000
 *
 * 문제)
 *  1. n * m 의 0,1 로 된 배열이 주어진다.
 *  2. 이 배열에서 1로된 가장 큰 정사각형의 넓이를 구하기
 *
 * 풀이)
 *  DP
 *    dp[i][j] : (i,j)를 우측하단으로 하는 가장 큰 정사각형의 변의 길이
 *    점화식
 *     - dp[i][j] = min(왼쪽, 위, 대각선) + 1 (단, 해당 칸이 1일 때)
 *
 */
class Problem1915 {

    private static int N;
    private static int M;
    private static int[][] board;

    public static void main(String args[]) throws IOException {
        input();
        int max = 0;
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (board[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.println(max * max); // 넓이 출력
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j) - '0';
            }
        }
    }
}
/*
4 4
0100
0111
1110
0010

1 1
1

1 2
11

2 1
1
1
 */

