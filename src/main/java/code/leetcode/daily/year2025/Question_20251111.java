package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/ones-and-zeroes/description/?envType=daily-question&envId=2025-11-11
 *
 * 제한)
 *  1 <= strs.length <= 600
 *  1 <= strs[i].length <= 100
 *  strs[i] consists only of digits '0' and '1'.
 *  1 <= m, n <= 100
 *
 * 문제)
 *  1. 0과 1로만 이루어진 문자열 배열 strs, 정수 m, n이 주어진다.
 *  2. m : 사용할 수 있는 0의 최대 개수
 *  3. n : 사용할 수 있는 1의 최대 개수
 *  4. strs의 부분집한 중에서, 0의 개수가 m 이하, 1의 개수가 n 이하인 가장 큰 부분집합의 크기를 반환
 *
 * 풀이)
 *  DP
 *  dp[i][j] : 0을 i개, 1을 j개 사용할 때 만들 수 있는 부분집합의 최대 크기
 *   - 역순으로 갱신 ( 이전 상태를 덮어쓰지 않도록 )
 *   - i = m 부터 zero 개수까지, j = n 부터 one 개수까지
 *
 */

public class Question_20251111 {
    public static void main(String args[]) throws IOException {
        String[] strs = {"10","0001","111001","1","0"};
        int m = 5;
        int n = 3;
        System.out.println(findMaxForm(strs, m, n));
    }

    public static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];

        for (String str : strs) {
            int zero = 0;
            int one = 0;
            for (char c : str.toCharArray()) {
                if (c == '0') zero++;
                else one++;
            }

            for (int i = m; i >= zero; i--) {
                for (int j = n; j >= one; j--) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                }
            }
        }

        return dp[m][n];
    }
}
