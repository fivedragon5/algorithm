package code.leetcode.daily.year2025.month04;

import java.io.IOException;

/**
 * https://leetcode.com/problems/find-numbers-with-even-number-of-digits/description/?envType=daily-question&envId=2025-04-30
 *
 * 제한)
 *  1 <= nums.length <= 500
 *  1 <= nums[i] <= 10^5
 *
 * 문제)
 *  1. nums 배열이 주어질 때, nums[i]의 자릿수가 짝수인 숫자의 개수를 구하라.
 *
 * 풀이)
 *  1. nums[i]를 String으로 변환한 후, length() 메서드를 사용하여 자릿수를 구한다.
 */

public class Question_20250430 {
    public static void main(String args[]) throws IOException {
        int[] nums = {12,345,2,6,7896};
        System.out.println(findNumbers(nums));

        int[] nums2 = {555,901,482,1771};
        System.out.println(findNumbers(nums2));
    }

    public static int findNumbers(int[] nums) {
        int count = 0;

        for (int num : nums) {
            if (String.valueOf(num).length() % 2 == 0) {
                count++;
            }
        }

        return count;
    }
}
