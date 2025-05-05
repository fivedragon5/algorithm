package code.leetcode.daily.year2025.month05;

import java.io.IOException;

/**
 * https://leetcode.com/problems/domino-and-tromino-tiling/?envType=daily-question&envId=2025-05-05
 *
 * 제한)
 *  1 <= n <= 1000
 *
 * 문제)
 * 1. 두 가지 종류의 타일이 있습니다: 2x1 크기의 도미노 모양과 트로미노 모양
 * 2. 정수 n이 주어졌을 때, 2 x n 크기의 판을 타일로 채우는 방법의 수 반환
 *  - 10^9 + 7로 나눈 나머지
 *
 * 풀이)
 *  점화식 : dp[i] = dp[i - 1] * 2 + dp[i - 3]
 *  초기값 : dp[0] = 1, dp[1] = 1, dp[2] = 2, dp[3] = 5
 *
 */

public class Question_20250505 {
    public static void main(String args[]) throws IOException {
        int n = 30;
        System.out.println(numTilings(n));
    }

    private static final int MOD = 1000000007;

    public static int numTilings(int n) {
        long[] dp = new long[1001];
        dp[0] = 1L; dp[1] = 1L; dp[2] = 2L; dp[3] = 5L;
        for (int i = 4; i <= n; i++) {
            dp[i] = (2L * dp[i - 1] + dp[i - 3]) % MOD;
        }
        return (int) dp[n];
    }
}
