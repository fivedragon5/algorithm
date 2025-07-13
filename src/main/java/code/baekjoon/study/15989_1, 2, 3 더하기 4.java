package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * https://www.acmicpc.net/problem/15989
 *
 * 제한)
 *  1 ≤ n ≤ 10,000
 *
 * 문제)
 *  정수 n이 주어졌을 땨, n을 1,2,3의 합으로 나타내는 방법의 수 구하기
 *   - 합을 이루고 있는 수의 순서만 다른 것은 같은것으로 친다.
 *  ex) n = 4
 *   1. 1+1+1+1
 *   2. 2+1+1 (1+1+2, 1+2+1)
 *   3. 2+2
 *   4. 1+3
 *
 * 풀이)
 *  DP
 *   dp[i] += dp[i-num] (num = 1,2,3)
 *   - dp[i] : i를 1,2,3의 합으로 나타내는 방법의 수 누적 합 이용
 *   - num = 1 : i를 1로 만들 수 있는 경우의 수
 *   - num = 2 : i를 2로 만들 수 있는 경우의 수
 *   - num = 3 : i를 3로 만들 수 있는 경우의 수
 */
class Problem15989 {

    private static int N;
    private static int[] testCases;

    public static void main(String args[]) throws IOException {
        input();
        int[] dp = new int[10001]; // 1,2,3의 합으로 나타내는 방법의 수
        dp[0] = 1;

        for (int num : new int[]{1, 2, 3}) {
            for (int i = num; i <= 10000; i++) {
                dp[i] += dp[i - num];
            }
        }

        for (int i = 0; i < N; i++) {
            System.out.println(dp[testCases[i]]);
        }
    }

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        testCases = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            testCases[i] = Integer.parseInt(st.nextToken());
        }
    }
}
/*
3
4
7
10

4
8
14

1
20

 */

