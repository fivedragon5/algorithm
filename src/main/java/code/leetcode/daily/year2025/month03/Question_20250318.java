package code.leetcode.daily.year2025.month03;

import java.io.IOException;

/**
 * https://leetcode.com/problems/longest-nice-subarray/description/?envType=daily-question&envId=2025-03-18
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *
 * 문제)
 *  1. Nice Array 를 만족하는 가장 긴 부분 배열의 길이를 반환
 *   - 좋은 부분 배열
 *    - 부분 배열 내에서 서로 다른 위치에 있는 모든 쌍의 원소들의 비트 AND 연산 결과가 0 인 경우.
 *    - 길이가 1인 부분 배열은 항상 "좋은" 부분 배열로 간주
 *
 * 풀이)
 *  1. left, right 변수 선언
 *  2. 사이에 있는 부분 배열의 bit and 연산 수행
 *  3. 만족 할 경우 right + 1, 만족하지 않을 경우 left + 1
 *  4. 만족할 경우 배열 길이 갱신
 */

public class Question_20250318 {
    public static void main(String args[]) throws IOException {
        int[] nums3 = new int[]{135745088,609245787,16,2048,2097152};
        System.out.println(longestNiceSubarray(nums3)); // 3

        int[] nums = new int[]{1,3,8,48,10};
        System.out.println(longestNiceSubarray(nums)); // 3

        int[] nums2 = new int[]{3,1,5,11,13};
        System.out.println(longestNiceSubarray(nums2)); // 1
    }

    public static int longestNiceSubarray(int[] nums) {

        int answer = 1; // 최소 값 1
        int numsLength = nums.length;
        int left = 0; int right = 1;
        while (right < numsLength) {
            if (checkNiceSubArray(nums, left, right)) {
                right++;
                answer = Math.max(answer, right - left);
            } else {
                left++;
            }
        }
        return answer;
    }

    private static boolean checkNiceSubArray(int[] nums, int left, int right) {
        for (int i = left; i <= right; i++) {
            for (int j = i + 1; j <= right; j++) {
                if ((nums[i] & nums[j]) != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
