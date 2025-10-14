package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.List;

/**
 * https://leetcode.com/problems/adjacent-increasing-subarrays-detection-i/description/?envType=daily-question&envId=2025-10-14
 * 제한)
 *  2 <= nums.length <= 100
 *  1 < 2 * k <= nums.length
 *  -1000 <= nums[i] <= 1000
 *
 * 문제)
 *  1. 정수배열 nums, 정수 k 가 주어짐
 *  2. 길이가 k인 두 개의 인접한 부분 배열이 모두 엄격히 증가하는 배열인지 판단하기
 *
 * 풀이)
 *  1. nums를 순회
 *   - for (int i = 0; i <= n - 2 * k; i++)
 *      - n - 2 * k : 길이가 k인 두 개의 인접한 부분 배열이 존재하기 위한 최소 길이
 *  2. 2개의 부분 배열이 증가하는 배열인지 확인
 *   - 범위 : [i 부터 i + k - 1] [i + k 부터 i + k + k - 1]
 *  3. 두 부분 배열이 모두 증가하는 배열이면 true 반환
 *  4. k = 1 경우, true 반환
 *
 */

public class Question_20251014 {
    public static void main(String args[]) throws IOException {
        List<Integer> nums3 = List.of(5,8,-2,-1);
        int k2 = 2;
        System.out.println(hasIncreasingSubarrays(nums3, k2)); // true

//        List<Integer> nums = List.of(2,5,7,8,9,2,3,4,3,1);
//        int k = 3;
//        System.out.println(hasIncreasingSubarrays(nums, k)); // true
//
//        List<Integer> nums2 = List.of(1,2,3,4,4,4,4,5,6,7);
//        k = 5;
//        System.out.println(hasIncreasingSubarrays(nums2, k)); // fase
    }

    private static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        if (k == 1) return true;
        int n = nums.size();
        for (int i = 0; i <= n - 2 * k; i++) {
            if (isIncreasing(nums, i, i + k - 1) && isIncreasing(nums, i + k, i + k + k - 1)) {
                return true;
            }
        }
        return false;
    }

    private static boolean isIncreasing(List<Integer> nums, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            if (nums.get(i - 1) >= nums.get(i)) {
                return false;
            }
        }
        return true;
    }
}
