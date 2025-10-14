package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.List;

/**
 * https://leetcode.com/problems/largest-divisible-subset/description/?envType=daily-question&envId=2025-04-06
 *
 * 제한)
 *  1 <= nums.length <= 1000
 *  1 <= nums[i] <= 2 * 10^9
 *  All the integers in nums are unique.
 *
 * 문제)
 *
 * 풀이)
 *
 */

public class Question_20250406 {
    public static void main(String args[]) throws IOException {
        int[] nums4 = new int[]{1,2,3};
        System.out.println(largestDivisibleSubset(nums4));

        int[] nums = new int[]{1,2,4,8};
        System.out.println(largestDivisibleSubset(nums));

        int[] nums2 = new int[]{1,2,3,5};
        System.out.println(largestDivisibleSubset(nums2));

        int[] nums3 = new int[]{1,8,1,8};
        System.out.println(largestDivisibleSubset(nums3));

    }

    public static List<Integer> largestDivisibleSubset(int[] nums) {
//        // 배수 찾기를 위해 정렬
//        Arrays.sort(nums);
//        int[] dp = new int[nums.length];
//        Arrays.fill(dp, 1);
//        for (int i = 0; i < nums.length - 1; i++) {
//        }

        return null;
    }
}
