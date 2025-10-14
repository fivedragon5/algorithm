package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/count-subarrays-where-max-element-appears-at-least-k-times/description/?envType=daily-question&envId=2025-04-29
 *
 * 제한)
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^6
 * 1 <= k <= 10^5
 *
 * 문제)
 *  1. nums 배열이 주어진다.
 *  2. k는 nums 배열에서 가장 큰 원소가 k번 이상 등장하는 부분 배열의 개수를 구하라.
 *
 * 풀이)
 *  1. nums 배열에서 가장 큰 원소를 찾기
 *  2. nums 배열에서 가장 큰 원소가 k번 이상 등장하는 부분 배열을 찾기
 *  3. 부분 배열의 개수를 구한다.
 *
 */

public class Question_20250429 {
    public static void main(String args[]) throws IOException {
        int[] maxValue = {1,3,2,3,3}; int k = 2;
        System.out.println(countSubarrays(maxValue, k));

        int[] maxValue2 = {1,4,2,1}; int k2 = 3;
        System.out.println(countSubarrays(maxValue2, k2));
    }

    public static long countSubarrays(int[] nums, int k) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int n = nums.length;
        int left = 0;
        int right = 0;
        int maxCount = 0;
        long result = 0L;

        while (right < n) {
            if (nums[right] == max) {
                maxCount++;
            }
            while (maxCount >= k) {
                result += (n - right); // max가 k번 이상 등장한 경우 모든 부분배열 가능
                if (nums[left] == max) {
                    maxCount--;
                }
                left++;
            }
            right++;
        }

        return result;
    }
}
