package code.programmers.normal;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/389480
 *
 * 제한)
 *  1 ≤ info의 길이 ≤ 40
 *  info[i]는 물건 i를 훔칠 때 생기는 흔적의 개수를 나타내며, [A에 대한 흔적 개수, B에 대한 흔적 개수]의 형태입니다.
 *  1 ≤ 흔적 개수 ≤ 3
 *  1 ≤ n ≤ 120
 *  1 ≤ m ≤ 120
 *
 * 문제)
 *  info[i][0] : A도둑이 i번째 물건을 훔첬을때 흔적을 남기는 수
 *  info[i][1] : B도둑이 i번째 물건을 훔첬을때 흔적을 남기는 수
 *  n : A도둑의 흔적이 n개 이상일 경우 경찰에 붙잡힘
 *  m : B도둑의 흔적이 n개 이상일 경우 경찰에 붙잡힘
 *
 *  1. 물건을 모두 훔치고 A도둑과 B도둑이 경찰에 잡히지 않는 A도둑의 흔적의 최소값을 반환
 *  2. 물건을 모두 훔칠 수 없을 경우 -1 반환
 *
 * 풀이)
 *
 */

class Lesson389480 {
    public static void main(String args[]) throws IOException {
        int[][] info = new int[][]{{1, 2}, {2, 3}, {2, 1}};
        int n = 4;
        int m = 4;
        System.out.println(solution(info, n, m));

        int[][] info2 = new int[][]{{1, 2}, {2, 3}, {2, 1}};
        int n2 = 1;
        int m2 = 7;
        System.out.println(solution(info2, n2, m2));

        int[][] info3 = new int[][]{{3, 3}, {3, 3}};
        int n3 = 7;
        int m3 = 1;
        System.out.println(solution(info3, n3, m3));
    }

    public static int solution(int[][] info, int n, int m) {
        // dp[i][j] : i(물건 수), j(B 도둑의 누적 흔적) : A도둑의 최소 흔적 k
        int[][] dp = new int[info.length + 1][m];
        for (int[] array : dp) {
            Arrays.fill(array, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        for (int i = 0; i < info.length; i++) {
            for (int j = 0; j < m; j++) {
                if(dp[i][j] == Integer.MAX_VALUE) continue;

                // A 도둑이 현재 물건을 가져가는 경우
                int newA = dp[i][j] + info[i][0];
                if (newA < n) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], newA);
                }

                // B 도둑이 현재 물건을 가져가는 경우
                int newB = j + info[i][1];
                if (newB < m) {
                    dp[i + 1][newB] = Math.min(dp[i + 1][newB], dp[i][j]);
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int value : dp[info.length]) {
            result = Math.min(result, value);
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
