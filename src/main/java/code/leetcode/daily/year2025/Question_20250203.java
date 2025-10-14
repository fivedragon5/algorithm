package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/longest-strictly-increasing-or-strictly-decreasing-subarray/description/?envType=daily-question&envId=2025-02-03
 *
 * 제한)
 *  1 <= nums.length <= 50
 *  1 <= nums[i] <= 50
 *
 * 문제)
 *  1. 배열 nums에서 증가 또는 감소하는 가장 긴 연속된 배열 의 길이를 반환하기
 *
 * 풀이)
 *  1. 주어진 nums를 순회하면서 증가, 감소에대한 카운트를 늘리기
 *   - 반복 마지막에 최대값 갱신 처리
 */

public class Question_20250203 {
    public static void main(String args[]) throws IOException {

        int[] nums = {1,4,3,3,2};
        System.out.println(longestMonotonicSubarray(nums));

        int[] nums2 = {3,3,3,3};
        System.out.println(longestMonotonicSubarray(nums2));

        int[] nums3 = {3,2,1};
        System.out.println(longestMonotonicSubarray(nums3));
    }

    public static int longestMonotonicSubarray(int[] nums) {
        int maxSubArrayLength = 1;
        int increasing = 1;
        int decreasing = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] < nums[i]) {
                increasing++;
                decreasing = 1;
            } else if (nums[i-1] > nums[i]) {
                decreasing++;
                increasing = 1;
            } else {
                increasing = 1;
                decreasing = 1;
            }
            maxSubArrayLength = Math.max(Math.max(maxSubArrayLength, increasing), decreasing);
        }
        return maxSubArrayLength;
    }
}
