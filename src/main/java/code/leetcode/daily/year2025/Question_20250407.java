package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/partition-equal-subset-sum/description/?envType=daily-question&envId=2025-04-07
 *
 * 제한)
 *  1 <= nums.length <= 200
 *  1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 배열 nums가 주어졌을 배열을 2개로 나누고 상황에 따라 true, false를 반환
 *   - true : 두개로 나눈 배열의 원소의 합이 같을 경우
 *   - false : 합이 다를 경우
 *
 * 풀이)
 *   DP dp[i] = 배열에서 뽑아낸 원소의 합이 i를 나타낼 수 있는지에 대한 여부
 *   1. 총 합이 홀수인지 확인
 *   2. 목표 합 계산
 *    - 목표합이 홀수인 경우 false
 *    - 목표합 : 총합 / 2
 *   3. DP배열 초기화 dp[0] = true
 *    - 아무것도 선택하지 않을 경우
 *   4. 목표합 ~ num[i] 까지 반복문
 *    - dp[currentSum] = dp[currentSum] || dp[currentSum - num[i]]
 *
 */

public class Question_20250407 {
    public static void main(String args[]) throws IOException {
        int[] nums4 = new int[]{1};
        System.out.println(canPartition(nums4));

        int[] nums = new int[]{1,5,11,5};
        System.out.println(canPartition(nums));

        int[] nums2 = new int[]{1,2,3,5};
        System.out.println(canPartition(nums2));

        int[] nums3 = new int[]{1,8,1,8};
        System.out.println(canPartition(nums3));

    }

    public static boolean canPartition(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
        }

        // 총 합이 홀수일 경우 false
        if (sum % 2 != 0) {
            return false;
        }

        int targetSum = sum/2;

        //dp[i] = 총 합이 i가 될 수 있는지에 대한 여부
        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int currentSum = targetSum; currentSum >= num; currentSum--) {
                dp[currentSum] = dp[currentSum] || dp[currentSum - num];
                if (dp[targetSum]) {
                    return true;
                }
            }
        }
        return dp[targetSum];
    }
}
