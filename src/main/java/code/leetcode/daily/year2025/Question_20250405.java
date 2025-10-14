package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/sum-of-all-subset-xor-totals/?envType=daily-question&envId=2025-04-05
 *
 * 제한)
 *  1 <= nums.length <= 12
 *  1 <= nums[i] <= 20
 *
 * 문제)
 *  1. 배열 nums가 주어졌을 때, 보든 부분 집합에 대해 XOR 연산한 값을 반환하기
 *
 * 풀이)
 *  1. DFS를 사용해서 모든 부분에 대해 XOR 연산한 값을 answer에 누적해서 더해주기
 */

public class Question_20250405 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{1,3};
        System.out.println(subsetXORSum(nums));

        int[] nums2 = new int[]{5,1,6};
        System.out.println(subsetXORSum(nums2));

    }

    public static int subsetXORSum(int[] nums) {
        return dfs(nums, 0, 0);
    }

    private static int dfs(int[] nums, int index, int currentXOR) {
        if (index == nums.length) {
            return currentXOR;
        }

        // 현재 요소를 포함하는 경우
        int include = dfs(nums, index + 1, currentXOR ^ nums[index]);

        // 현재 요소를 포함하지 않는 경우
        int exclude = dfs(nums, index + 1, currentXOR);

        return include + exclude;
    }
}
