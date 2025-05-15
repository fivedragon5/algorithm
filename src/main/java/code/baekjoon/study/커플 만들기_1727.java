package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/1727
 *
 * 제한)
 *  1 ≤ n, m ≤ 1,000
 *  1 ≤ 성격 수치 ≤ 1,000,000
 *
 * 문제)
 *  1. 여자친구가 없는 남자 n명과 각각의 성격을 수치화한 값이 주어진다.
 *  2. 마찬가지로 남자친구가 없는 여자m명과 각각ㄱ의 성격을 수치화한 값이 주어진다.
 *  3. 우선 최대한 많은 커플을 만들고 각 커플을 이루는 두 사람의 성격의 차이의 합이 최소가 되도록 커플을 만들어야 한다.
 *  4. 성격 차이의 합의 최솟값을 구하기
 *
 * 풀이)
 *  남,여 최대 1000쌍 * 수치 최대 1,000,000 = 1,000,000,000 -> int 범위 가능
 *  dp[i][j] : i번째 남자, j번째 여자 까지 고려했을 경우 성격 차이 합의 최소값
 *    점화식
 *     - i == j : i번째 남자와 j번째 여자가 커플이 되는 경우
 *      - dp[i][j] = dp[i-1][j-1] + abs(M_SCORES[i] - W_SCORES[j])
 *     - i > j : 남자 수가 더 많은 경우
 *      - dp[i][j] = min(dp[i-1][j], dp[i-1][j-1] + abs(M_SCORES[i] - W_SCORES[j]))
 *     - j > i : 여자 수가 더 많은 경우
 *     - dp[i][j] = min(dp[i][j-1], dp[i-1][j-1] + abs(M_SCORES[i] - W_SCORES[j]))
 *
 */
class Problem1727 {

    private static int N; // 남자 수
    private static int M; // 여자 수
    private static int[] M_SCORES; // 남자 성격 수치를 담은 배열
    private static int[] W_SCORES; // 남자 성격 수치를 담은 배열

    public static void main(String args[]) throws IOException {
        input();
        Arrays.sort(M_SCORES);
        Arrays.sort(W_SCORES);
        int[][] dp = new int[N+1][M+1]; // dp[i][j] : i번째 남자, j번째 여자 까지 고려했을 경우 성격 차이 합의 최소값

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (i == j) {
                    dp[i][j] = dp[i-1][j-1] + Math.abs(M_SCORES[i-1] - W_SCORES[j-1]);
                } else if (i > j) {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i-1][j-1] + Math.abs(M_SCORES[i-1] - W_SCORES[j-1]));
                } else {
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j-1] + Math.abs(M_SCORES[i-1] - W_SCORES[j-1]));
                }
            }
        }
        System.out.println(dp[N][M]);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        M_SCORES = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            M_SCORES[i] = Integer.parseInt(st.nextToken());
        }
        W_SCORES = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            W_SCORES[i] = Integer.parseInt(st.nextToken());
        }
    }
}
/*
2 1
10 20
15

3 3
3 9 2
8 10 10

14

4 3
1 7 9 300
6 8 10

7
 */

