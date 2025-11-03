package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/minimum-time-to-make-rope-colorful/?envType=daily-question&envId=2025-11-03
 *
 * 제한)
 *  n == colors.length == neededTime.length
 *  1 <= n <= 10^5
 *  1 <= neededTime[i] <= 10^4
 *  colors contains only lowercase English letters.
 *
 * 문제)
 *  1. 색깔이 같은 인접한 풍선을 제거하여 모든 인접한 풍선이 다른 색이 되도록 만들고자 한다.
 *  2. 풍선 i를 제거하는데 필요한 시간은 neededTime[i]
 *  3. 모든 인접한 풍선이 다른 색이 되도록 만드는 최소 시간을 반환
 *
 * 풀이)
 *  1. 이전 문자와 현재 문자가 같은지 확인
 *  2. 같다면, 같은 문자들 중에서 제거하는데 드는 시간이 가장 적은 풍선을 제거
 *   - 제거하면서 제거에 필요한 필요한 시간 누적해서 더해줌
 *  3. 이를 반복하여 모든 인접한 풍선이 다른 색이 되도록 만듦
 *  4. 최종적으로 누적된 시간을 반환
 *
 */

public class Question_20251103 {
    public static void main(String args[]) throws IOException {
        String colors = "abaac";
        int[] neededTime = {1,2,3,4,5};
        System.out.println(minCost(colors, neededTime));

        colors = "abc";
        neededTime = new int[] {1,2,3};
        System.out.println(minCost(colors, neededTime));

        colors = "aabaa";
        neededTime = new int[] {1,2,3,4,1};
        System.out.println(minCost(colors, neededTime));
    }

    private static int minCost(String colors, int[] neededTime) {
        int result = 0;
        int cost = neededTime[0];
        char prevChar = colors.charAt(0);
        for (int i = 1; i < colors.length(); i++) {
            char currentChar = colors.charAt(i);
            if (currentChar == prevChar) {
                for (int j = i; j < colors.length(); j++) {
                    if (colors.charAt(j) != prevChar) {
                        break;
                    }
                    if (neededTime[j] < cost) {
                        result += neededTime[j];
                    } else {
                        result += cost;
                        cost = neededTime[j];
                    }
                    i = j;
                }
            } else {
                cost = neededTime[i];
                prevChar = currentChar;
            }
        }
        return result;
    }
}
