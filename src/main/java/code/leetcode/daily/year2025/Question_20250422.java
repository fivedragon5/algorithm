package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-the-number-of-ideal-arrays/description/?envType=daily-question&envId=2025-04-22
 *
 * 제한)
 *  2 <= n <= 10^4
 *  1 <= maxValue <= 10^4
 *
 * 문제)
 *  1. n, maxValue는 이상적인 배열을 만들기 위해 쓰인다.
 *  2. 이상적인 배열은 아래 조건을 만족한다
 *   - 0 <= i < n
 *   - 1 <= arr[i] <= maxValue
 *   - arr[i] % arr[i-1] == 0
 *  3. 주어진 n, maxValue로 만들 수 있는 배열의 수를 반환
 *   - 값이 클 수 있으니 10^9 + 7 로 나눈 나머지를 반환하기
 *
 * 풀이)
 *
 */

public class Question_20250422 {
    public static void main(String args[]) throws IOException {
        int n = 2; int maxValue = 5;
        System.out.println(idealArrays(n, maxValue));
    }

    public static int idealArrays(int n, int maxValue) {
        int min = Math.min(n, 14);
        // dp[i][j] = 길이가 i이고 마지막 원소가 j인 이상적인 배열의 개수
        int[][] dp = new int[100000][15];

        for (int i = 0; i < maxValue; i++) {

        }

        for (int i = 1; i < min; i++) {
            for (int j = 2; i * j <= maxValue; j++) {
                for (int k = 1; k < min; k++) {
                    dp[k + 1][i * j] += dp[k][i];
                }
            }
        }

        for (int i = 1; i <= maxValue; i++) {
            dp[i][1] = 1;

        }

        // dp[i][j] = dp[i - 1][k]

        // dp[1][1] = 1
        // dp[2][1] =

        return 0;
    }

    private static int combination(int number, int maxValue) {
        return 0;
    }
}
