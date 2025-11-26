package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/paths-in-matrix-whose-sum-is-divisible-by-k/description/?envType=daily-question&envId=2025-11-26
 *
 * 제한)
 *  m == grid.length
 *  n == grid[i].length
 *  1 <= m, n <= 5 * 10^4
 *  1 <= m * n <= 5 * 10^4
 *  0 <= grid[i][j] <= 100
 *  1 <= k <= 50
 *
 * 문제)
 *  1. m * n 크기의 정수 행렬 grid와 정수 k가 주어진다.
 *  2. (0,0)에서 (m-1,n-1)까지 아래, 오른쪽으로만 이동할 수 있다.
 *  3. 경로 위의 원소들의 합이 k로 나누어 떨어지는 경로의 수를 반환하라.
 *  4. 결과는 10^9 + 7로 나눈 나머지로 반환하라.
 *
 * 풀이)
 *  DP (Dynamic Programming)
 *   - 점화식 : dp[i][j][r] = (0,0)에서 (i,j)까지 왔을 때, 경로 합을 k로 나눈 나머지가 r인 경로의 수
 *  1. 3차원 DP 배열을 생성한다.
 *  2. 초기값을 설정한다. row = 0 일때의 값 설정
 *  3. 점화식을 이용하여 DP 배열을 채운다.
 *   - dp[i][j][r] = dp[i-1][j][(r - grid[i][j] + k) % k] + dp[i][j-1][(r - grid[i][j] + k) % k]
 *   - 위에서 아래로 오는 경우와 왼쪽에서 오른쪽으로 오는 경우를 더한다.
 *  4. 최종적으로 dp[m-1][n-1][0] 값을 반환한다.
 *   - MOD로 나누는 이유
 *   - 경로의 수가 매우 클 수 있으므로, 오버플로우를 방지하기 위해서이다.
 */

public class Question_20251126 {
    public static void main(String args[]) throws IOException {
        int[][] grid = {
            {5,2,4},
            {3,0,5},
            {0,7,2}
        };
        int k = 3;
        System.out.println(numberOfPaths(grid, k));
    }

    private static final int MOD = 1_000_000_007;

    public static int numberOfPaths(int[][] grid, int k) {
        int row = grid.length;
        int col = grid[0].length;
        int[][][] dp = new int[row][col][k];

        // 초기값 설정
        int beforeRemainder = grid[0][0] % k;
        dp[0][0][beforeRemainder] = 1;
        for (int i = 1; i < col; i++) {
            int remainder = (beforeRemainder + grid[0][i]) % k;
            dp[0][i][remainder] = 1;
            beforeRemainder = remainder;
        }

        for (int i = 1; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int r = 0; r < k; r++) {
                    int newRemainder = (r + grid[i][j]) % k;
                    dp[i][j][newRemainder] = (dp[i][j][newRemainder] + dp[i - 1][j][r]) % MOD;
                    if (j > 0) {
                        dp[i][j][newRemainder] = (dp[i][j][newRemainder] + dp[i][j - 1][r]) % MOD;
                    }
                }
            }
        }

        return dp[row - 1][col - 1][0];
    }
}
