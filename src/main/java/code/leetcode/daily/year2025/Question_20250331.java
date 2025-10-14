package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/put-marbles-in-bags/description/?envType=daily-question&envId=2025-03-31
 *
 * 제한)
 *  1 <= k <= weights.length <= 10^5
 *  1 <= weights[i] <= 10^9
 *
 * 문제)
 *  weights[i] : i번째 구슬의 무게
 *  k : 가방의 수
 *  1. 각 가방에 구슬을 넣고 해당 가방의 무게는 i ~ j 구슬을 넣을 경우 weights[i] + weights[j] 로 계산 됨
 *   - 구슬의 배열 순서대로 넣을 수 있음
 *  2. 모든 배낭의 구슬의 합을 최대 합 - 최소 합 을 구하기
 *   - 비어있는 가방을 있을 수 없음
 *
 * 풀이
 *  1. 구슬배열에서 인접한 두 구슬의 합을 계산 sums[i]
 *  2. sums 정렬 (오름차순)
 *  3. 점수 계산
 *   - 최소 : sums[0] ~ sums[k-1]
 *   - 최대 : sums[n] ~ sums[n - 2 - i]
 */

public class Question_20250331 {
    public static void main(String args[]) throws IOException {

        int[] weights = new int[]{1,3,5,1}; int k = 2;
        System.out.println(putMarbles(weights, k));

        int[] weights2 = new int[]{1, 3}; int k2 = 2;
        System.out.println(putMarbles(weights2, k2));
    }

    public static long putMarbles(int[] weights, int k) {
        int n = weights.length;
        if (k == 1) {
            return 0;
        }

        long[] sums = new long[n - 1];
        for (int i = 0; i < n - 1; i++) {
            sums[i] = weights[i] + weights[i + 1];
        }

        Arrays.sort(sums);

        long minScore = weights[0] + weights[n - 1];
        long maxSum = weights[0] + weights[n - 1];
        for (int i = 0; i < k - 1; i++) {
            minScore += sums[i];
            maxSum += sums[n - 2 - i]; // sums[n - 2] ~ sums[n - k] 까지의 값이 가장 큰 값들
        }

        return maxSum - minScore;
    }
}
