package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/number-of-smooth-descent-periods-of-a-stock/description/?envType=daily-question&envId=2025-12-15
 *
 * 제한)
 *  1 <= prices.length <= 10^5
 * 1 <= prices[i] <= 10^5
 *
 * 문제)
 *  1. 정수 배열 prices가 주어집니다. prices[i]는 주식의 i번째 날의 가격을 나타냅니다.
 *  2. prices[i] = i번째 날의 주가
 *  3. 부드러운 하락 기간(Smooth Descent Period)은 아래 조건을 만족하는 연속적인 날들의 부분 배열입니다.
 *   - 두 번째 날부터는 매일 주가가 바로 이전 날보다 정확히 1만큼 낮아야 합니다. prices[i] == prices[i-1] - 1
 *   - 단 첫 번째 날은 이 규칙에서 예외
 *  4. 이때 주어진 주식 가격 배열에서 모든 부드러운 하락 기간의 수를 반환하는 함수를 작성하세요.
 *
 * 풀이)
 *  DP
 *   - dp[i] : 인덱스 i 에서 끝나는 가장 긴 부드러운 하락 기간의 길이
 *      - dp[0] = 1 (첫 날은 항상 길이1)
 *      - i >= 1 일 때:
 *          - prices[i] == prices[i+1] - 1면, 하락이 이어지므로 dp[i] = dp[i - 1] + 1
 *          - 아니면, 하락이 끊겼으므로 dp[i] = 1
 *      - dp[i]는 i에서 끝나는 부드러운 하락 길이 . 개수
 *   - result = dp[0] + dp[1] + ... + dp[n-1]
 *
 */

public class Question_20251215 {
    public static void main(String args[]) throws IOException {
        int[] prices = {3,2,1,4};
        System.out.println(getDescentPeriods(prices));
    }

    public static long getDescentPeriods(int[] prices) {
        int n = prices.length;
        long result = 0;
        int[] dp = new int[n];

        dp[0] = 1;
        result += dp[0];

        for (int i = 1; i < n; i++) {
            if (prices[i] == prices[i - 1] - 1) {
                dp[i] = dp[i - 1] + 1;
            } else {
                dp[i] = 1;
            }
            result += dp[i];
        }

        return result;
    }
}
