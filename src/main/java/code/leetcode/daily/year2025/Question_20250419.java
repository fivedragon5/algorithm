package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.Arrays;

/**
 * https://leetcode.com/problems/count-the-number-of-fair-pairs/description/?envType=daily-question&envId=2025-04-19
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  nums.length == n
 *  -10^9 <= nums[i] <= 10^9
 *  -10^9 <= lower <= upper <= 10^9
 *
 * 문제)
 *  아래 조건을 만족하는 쌍을 찾기
 *   1. 0 <= i < j < n
 *   2. lower <= nums[i] + nums[j] <= upper
 *
 * 풀이)
 *  1. 이중 for O(n^2) 약 10^10 으로 시간 초과 예상
 *
 *  2. 정렬, 이분탐색 이용
 *   - 조건을 만족하는 최소, 최대 인덱스를 구하고 최대 - 최소 인덱스로 가능한 쌍을 모두 찾기
 *   - *Lower, Upper BinarySearch*
 */

public class Question_20250419 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{0,1,7,4,4,5}; int lower = 3; int upper = 6;
        System.out.println(countFairPairs(nums, lower, upper));

        int[] nums2 = new int[]{1,7,9,2,5}; int lower2 = 11; int upper2 = 11;
        System.out.println(countFairPairs(nums2, lower2, upper2));
    }

    public static long countFairPairs(int[] nums, int lower, int upper) {
        long pairCount = 0;
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n - 1; i++) {
            int currentNum = nums[i];
            int min = lower - currentNum;
            int minIndex = findIndexLower(nums, min, i + 1, n);
            int max = upper - currentNum;
            int maxIndex = findIndexUpper(nums, max, i + 1, n);

            pairCount += maxIndex - minIndex;
        }
        return pairCount;
    }

    private static int findIndexLower(int[] list, int target, int start, int n) {
        int left = start;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
             if (list[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int findIndexUpper(int[] list, int target, int start, int n) {
        int left = start;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2;
            if (list[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}
