package code.leetcode.daily.year2025.month04;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-i/?envType=daily-question&envId=2025-04-02
 *
 * 제한)
 *  3 <= nums.length <= 100
 *  1 <= nums[i] <= 10^6
 *
 * 문제)
 *  1. i < j < k 를 만족하고 아래의 연산으로 나타낼 수 있는 가장 큰 수를 반환
 *   - (nums[i] - nums[j]) * nums[k]
 *
 * 풀이)
 *  1. 모든 수를 연산해서 가장 큰 값을 반환
 */

public class Question_20250402 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{12,6,1,2,7};
        System.out.println(maximumTripletValue(nums));

        int[] nums2 = new int[]{1,10,3,4,19};
        System.out.println(maximumTripletValue(nums2));
    }

    public static long maximumTripletValue(int[] nums) {
        long maxValue = 0;
        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int firstValue = nums[i];
            for (int j = i + 1; j < n - 1; j++) {
                int secondValue = nums[j];
                for (int k = j + 1; k < n; k++) {
                    maxValue = Math.max(maxValue, (long) (firstValue - secondValue) * nums[k]);
                }
            }
        }
        return maxValue;
    }
}
