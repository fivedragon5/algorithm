package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-ascending-subarray-sum/description/?envType=daily-question&envId=2025-02-04
 *
 * 제한)
 *  1 <= nums.length <= 100
 *  1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 배열 nums에서 오름차순 SubArray를 추출해서 SubArray들의 합이 최대인 값을 반환하기
 *
 * 풀이)
 *
 */

public class Question_20250204 {
    public static void main(String args[]) throws IOException {

        int[] nums = {10,20,30,5,10,50};
        System.out.println(maxAscendingSum(nums));

        int[] nums2 = {10,20,30,40,50};
        System.out.println(maxAscendingSum(nums2));

        int[] nums3 = {12,17,15,13,10,11,12};
        System.out.println(maxAscendingSum(nums3));
    }

    public static int maxAscendingSum(int[] nums) {
        int answer = nums[0];
        int increase = nums[0];
        for (int i = 1; i < nums.length; i++) {
           if (nums[i-1] < nums[i]) {
               increase += nums[i];
               answer = Math.max(increase, answer);
           } else {
               increase = nums[i];
           }
        }
        return answer;
    }
}
