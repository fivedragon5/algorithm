package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-absolute-sum-of-any-subarray/description/?envType=daily-question&envId=2025-02-26
 *
 * 제한)
 * 1 <= nums.length <= 105
 * -104 <= nums[i] <= 104
 *
 * 문제)
 *  1. 배열 nums의 부분합의 절대값 최대값을 반환
 *
 * 풀이)
 *  1. 배열을 순회하면서 누적 최대, 최소 합을 갱신
 *  2. max - min 은 sum 이 가질 수 있는 가장 큰 변화량(최대 - 최소)를 절대값으로 반환
 *
 */

public class Question_20250226 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{1, -3, 2, 3, -4};
        System.out.println(maxAbsoluteSum(nums));

        int[] nums2 = new int[]{2,-5,1,-4,3,-2};
        System.out.println(maxAbsoluteSum(nums2));
    }

    public static int maxAbsoluteSum(int[] nums) {
        int sum = 0; int min = 0; int max = 0;
        for (int num : nums) {
            sum += num;
            if (sum > max) max = sum;
            if (sum < min) min = sum;
        }
        return Math.abs(max - min);
    }
}
