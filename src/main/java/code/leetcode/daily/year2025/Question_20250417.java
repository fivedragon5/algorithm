package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-equal-and-divisible-pairs-in-an-array/description/?envType=daily-question&envId=2025-04-17
 *
 * 제한)
 *  1 <= nums.length <= 100
 *  1 <= nums[i], k <= 100
 *
 * 문제)
 *  1. 0 <= i < j < n, nums[i] == nums[j] &&  i * j % k == 0 를 만족하는 (i,j) 쌍을 찾기
 *
 * 풀이)
 *  1. i,j 2중 for문 간단한 구현문제
 *
 */

public class Question_20250417 {
    public static void main(String args[]) throws IOException {

        int[] nums2 = new int[]{10,2,3,4,9,6,3,10,3,6,3,9,1};
        int k2 = 4;
        System.out.println(countPairs(nums2, k2));

        int[] nums3 = new int[]{3,1,2,2,2,1,3};
        int k3= 2;
        System.out.println(countPairs(nums3, k3));

        int[] nums = new int[]{1,2,3,4};
        int k = 1;
        System.out.println(countPairs(nums, k));
    }

    public static int countPairs(int[] nums, int k) {
        int answer = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int num_i = nums[i];
            for (int j = i + 1; j < n; j++) {
                if (num_i == nums[j] && (i * j) % k == 0) {
                    answer++;
                }
            }
        }
        return answer;
    }
}
