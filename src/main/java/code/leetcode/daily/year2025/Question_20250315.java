package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/house-robber-iv/description/?envType=daily-question&envId=2025-03-15
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *  1 <= k <= (nums.length + 1)/2
 *
 * 문제)
 *  1. 주어진 길을따라 연속된 집들이 있다 nums
 *  2. 각각의 집에는 nums[i] 만큼 돈이 있다
 *  3. 도둑은 이 집들에서 돈을 훔치려 하지만 인접한 집은 돈을 훔칠 수 없다.
 *  4. 도둑은 각각의 집에서 훔친 돈중 가장 많은 금액의 집에 돈만 가질 수 있다.
 *  5. 도둑이 k개의 집을 훔칠 수 있다고 했을때, 가장 적은 금액을 반환하기.
 *
 * 풀이)
 *  1. 훔칠 수 있는 돈에 대해 이분 탐색을 진행
 *   - left : min(nums)
 *   - right : max(nums)
 *  2. nums를 순회하면서 인접하지 않고 mid 이하의 돈을 훔칠 수 있는집이 k개 이상인지 확인하는 함수 구현
 *   - canSteal
 *    - true  : right, result 갱신
 *    - false : left 갱신
 *
 */

public class Question_20250315 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{2,3,5,9}; int k = 2;
        System.out.println(minCapability(nums, k));  // 5

        int[] nums2 = new int[]{2,7,9,3,1}; k = 2;
        System.out.println(minCapability(nums2, k));  // 2
    }

    public static int minCapability(int[] nums, int k) {
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int left = min;
        int right = max;
        int result = max;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (canSteal(nums, k, mid)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    private static boolean canSteal(int[] nums, int k, int mid) {
        int countSteal = 0;
        for (int i = 0; i < nums.length; i++) {
            if (mid >= nums[i]) {
                countSteal++;
                i++;
            }
        }
        return countSteal >= k;
    }
}
