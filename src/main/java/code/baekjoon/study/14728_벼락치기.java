package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/14728
 * 제한)
 *  1 ≤ N ≤ 100
 *  1 ≤ T ≤ 10,000
 *  1 ≤ K ≤ 1,000
 *  1 ≤ S ≤ 1,000
 *
 * 문제)
 *  1. 시험의 단원 갯수 N, 시험 까지 공부 할 수 있는 총 시간이 주어진다.
 *  2. 각 단원별 예상 공부시간, 단원 문제의 배점 이 주어젔을 경우 얻을 수 있는 최대 점수를 구하기
 *   - 여러 단원을 융합한 문제는 출제하지 않음
 *   - 한 단원에 한 문제를 출제, 단 그단원에 모든 내용을 알고 있어야 풀 수 있는 문제가 출제
 *
 * 풀이)
 *  DP
 *   점화식 : DP[i][j] = i번째 단원까지 고려하고 공부할 수 있는 시간이 j일 경우 최대 점수
 */
class Problem14728 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String args[]) throws IOException {
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 시험 단원 개수
        int T = Integer.parseInt(st.nextToken()); // 공부 할 수 있는 시간
        int[][] subjectAndPoint = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int point = Integer.parseInt(st.nextToken());
            subjectAndPoint[i][0] = time;
            subjectAndPoint[i][1] = point;
        }

        // DP[i][j] = i번째 단원까지 고려하고 공부할 수 있는 시간이 j일 경우 최대 점수
        int[][] dp = new int[N + 1][T + 1];
        dp[0][0] = 0; // dp 초기값 설정

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= T; j++) {
                int time = subjectAndPoint[i-1][0];
                int point = subjectAndPoint[i-1][1];
                if (j >= time) {
                    // 현재 단원을 공부할 경우
                    dp[i][j] = Math.max(dp[i - 1][j - time] + point, dp[i-1][j]);
                } else {
                    // 현재 단원을 공부하지 않을 경우
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j]);
                }
            }
        }
        System.out.println(dp[N][T]);
    }
}
/*
3 310
50 40
100 70
200 150

220
 */
