package code.baekjoon.study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *
 * https://www.acmicpc.net/problem/9655
 * 제한)
 * 1 <= N <= 1,000
 * SK -> CY 순서이고 마지막에 돌을 가져간 사람이 이기게 된다.
 * 돌은 한번에 1개 또는 3개 가져갈 수 있다
 * 1 -> SK
 * 2 -> CY
 * 3 -> SK
 * 4 -> CY
 * 5 -> SK
 * 6 -> CY
 * 홀수 짝수 로 풀이 가능
 */

class Problem9655 {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int dp[] = new int[1001];

        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.min(dp[i-1] + 1, dp[i-3] + 1);
        }

        if (dp[N] % 2 == 1) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}

