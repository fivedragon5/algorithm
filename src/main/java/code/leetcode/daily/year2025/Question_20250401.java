package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/solving-questions-with-brainpower/description/?envType=daily-question&envId=2025-04-01
 *
 * 제한)
 * 1 <= questions.length <= 10^5
 * 1 <= points[i], brainpower[i] <= 10^5
 * questions[i].length == 2
 *
 * 문제)
 *  1. 2차원 배열이 주어진다
 *   - questions[i] [point, brainpower]
 *   - point : 해당 문제의 점수, brainpower : 앞으로 풀 수 없는 문제의 수
 *  2. i번째 문제를 풀거나 건너 뛸 수 있다.
 *  3. 시험에서 얻을 수 있는 최대 점수를 반환하기
 *
 * 풀이)
 *  DP O(n)
 *  1. dp[i] = i번째 문제부터 마지막 문제까지 풀었을 경우 최대 점수
 *   점화식 : dp[i] = Math.max(dp[i + 1], point[i] + dp[i + brainpower[i]])
 *   dp[i+1]
 *    - dp[i] 문제를 스킵하고 다음 문제를 푼 경우
 *   point[i] + dp[i + brainpower[i]]
 *    - i번째 문제를 풀고 + brainpower 이후의 문제 중 가장 최대 점수의 합을 dp[i]에 저장
 */

public class Question_20250401 {
    public static void main(String args[]) throws IOException {
        int[][] questions4 = new int[][]{{21,5},{92,3},{74,2},{39,4},{58,2},{5,5},{49,4},{65,3}};
        System.out.println(mostPoints(questions4));

        int[][] questions = new int[][]{{3,2},{4,3},{4,4},{2,5}};
        System.out.println(mostPoints(questions));

        int[][] questions2 = new int[][]{{1,1},{2,2},{3,3},{4,4},{5,5}};
        System.out.println(mostPoints(questions2));

        int[][] questions3 = new int[][]{{12,46},{78,19},{63,15},{79,62},{13,10}};
        System.out.println(mostPoints(questions3));
    }

    public static long mostPoints(int[][] questions) {
        long answer = questions[0][0];
        // dp[i] : i ~ 마지막 문제까지 풀었을 때 얻을 수 있는 최대 점수
        int n = questions.length;
        long[] dp = new long[n];
        dp[n - 1] = questions[n - 1][0];
        // 해당 문제를 풀었을 경우, 풀지 않았을 경우에 대한 최대값 비교
        for (int i = n - 2; i >= 0 ; i--) {
            long point = questions[i][0];
            int brainpower = questions[i][1];
            if (i + brainpower + 1 >= n) {
                dp[i] = Math.max(dp[i + 1], point);
            } else {
                dp[i] = Math.max(dp[i + 1], point + dp[i + brainpower + 1]);
            }
            answer = Math.max(dp[i], answer);
        }
        return answer;
    }
}
