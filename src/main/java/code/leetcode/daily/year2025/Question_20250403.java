package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-value-of-an-ordered-triplet-ii/description/?envType=daily-question&envId=2025-04-03
 *
 * 제한)
 *  3 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^6
 *
 * 문제)
 *  1. i < j < k 를 만족하고 아래의 연산으로 나타낼 수 있는 가장 큰 수를 반환
 *   - (nums[i] - nums[j]) * nums[k]
 *
 * 풀이)
 *  1. nums에서 maxNumArray를 만들어 i번째까지 가장 큰 수를 저장
 *  2. suffixNumArray[i] = Math.max(num[i] ~ nums[n]) 가장 큰 수 저장
 *  3. maxNumArray[i - 1] - nums[i]) * suffixNumArray[i+1] 연산
 */

public class Question_20250403 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{12,6,1,2,7};
        System.out.println(maximumTripletValue(nums));

        int[] nums2 = new int[]{1,10,3,4,19};
        System.out.println(maximumTripletValue(nums2));
    }

    public static long maximumTripletValue(int[] nums) {
        long maxValue = 0;
        int n = nums.length;
        int currentMaxNum = 0;

        int[] maxNumArray = new int[n];
        int[] suffixNumArray = new int[n];

        for (int i = 0; i < n; i++) {
            currentMaxNum = Math.max(nums[i], currentMaxNum);
            maxNumArray[i] = currentMaxNum;
        }

        currentMaxNum = nums[n-1];

        for (int i = n-1; i > 0; i--) {
            currentMaxNum = Math.max(nums[i], currentMaxNum);
            suffixNumArray[i] = currentMaxNum;
        }

        for (int i = 1; i < n - 1; i++) {
            maxValue = Math.max((long) (maxNumArray[i - 1] - nums[i]) * suffixNumArray[i+1], maxValue);
        }

        return maxValue;
    }
}
