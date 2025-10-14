package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/special-array-i/description/?envType=daily-question&envId=2025-02-01
 * 제한)
 *  1 <= nums.length <= 100
 *  1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 주어진 nums이 특별한 규칙을 만족하는지 확인
 *   - 홀수 -> 짝수 -> 홀수 || 짝수 -> 홀수 -> 짝수     (O)
 *   - 홀수 -> 홀수 || 짝수 -> 짝수                   (X)
 *  2. 조건을 만족하면 true, 만족하지 않으면 false 를 반환하기
 *
 * 풀이)
 *  1. nums를 순회하면서 실패하면 바로 false 반환
 *      - 반복문이 끝나면 true 반환
 */

public class Question_20250201 {
    public static void main(String args[]) throws IOException {

        int[] nums = {2,1,4};
        System.out.println(isArraySpecial(nums));

        int[] nums2 = {4,3,1,6};
        System.out.println(isArraySpecial(nums2));
    }

    private static boolean isArraySpecial(int[] nums) {
        boolean isOddNumber = nums[0] % 2 == 0;
        for (int i = 1; i < nums.length; i++) {
            if (isOddNumber == (nums[i] % 2 == 0)) {
                return false;
            }
            isOddNumber = nums[i] % 2 == 0;
        }
        return true;
    }
}
