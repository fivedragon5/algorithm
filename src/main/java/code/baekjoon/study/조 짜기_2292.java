package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/2292
 *
 * 제한)
 *  1 ≤ N ≤ 1,000
 *  0 ≤ 점수 ≤ 10,000
 *
 * 문제)
 *  1. 학생 N명이 주어진다.
 *  2. 학생들을 조로 묶어 잘 짜여진 정도의 최대값을 구하기
 *   - 잘 짜여진 조 : 조원중에 가장 높은 점수 - 가장 낮은 점수
 *   - 나이순서대로 정렬 되어 있음 : 조를 만들때 나이순서대로 정렬된 상태에서 조를 만들어야 함
 *
 * 풀이)
 *  DP O(N^2)
 *   dp[i] : i 번째 학생까지 고려 했을 경우 잘 짜여진 조의 최대 값
 *   dp[i]의 값 갱신
 *    - dp[j-1] + Max(score[i ~ j]) - Min([i ~ j])
 *    - 구간을 나누어 탐색 (1번 ~ N번 학생까지 순차 탐색)
 *      - ex) 3(1) 5(2) 1(3) 7(4) 8(5) 3(6)
 *            dp[4] : dp[2] + 3 ~ 4 번 학생중 점수 max - min 값 = 7 - 1
 *            dp[4] : dp[1] + 2 ~ 4 번 학생중 점수 max - min 값 = 7 - 1
 *            ...
 *   각 구간별 score의 max, min을 memoization시 효율성 올라갈듯
 *
 */
class Problem2292 {

    private static int N;
    private static int[] scores;

    public static void main(String args[]) throws IOException {
        input();
        // dp[i] : i 번째 학생까지 고려 했을 경우 최대값
        int[] dp = new int[N + 1];
        dp[0] = 0;

        for (int i = 1; i <= N; i++) {
            int max = scores[i];
            int min = scores[i];
            for (int j = i - 1; j >= 1; j--) {
                max = Math.max(max, scores[j]);
                min = Math.min(min, scores[j]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max - min);
            }
        }
        System.out.println(dp[N]);
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        String input = st.nextToken();
        N = Integer.parseInt(input);
        scores = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }
    }
}
/*
10
2 5 7 1 3 4 8 6 9 3

20

2
2 5

1
1
 */

