package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/count-the-number-of-good-subarrays/description/?envType=daily-question&envId=2025-04-16
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i], k <= 10^9
 *
 *  10000000000 - n - 2
 *
 * 문제)
 *  1. i < j & arr[i] == arr[j] 를 k개 이상 만족하는 subarray 갯수를 반환하기
 *
 * 풀이)
 *  Sliding Window, 누적합
 *  1. 숫자의 빈도수를 HashMap 에 저장
 *  2. nums를 순회하면서 pairCount가 k를 넘긴 시점에 left, pairCount 갱신
 *   - pairCount >= k 넘긴 시점에 right가 늘어나기에 쌍은 줄어들지 않음
 *
 */

public class Question_20250416 {
    public static void main(String args[]) throws IOException {

        int[] nums3 = new int[]{2,1,3,1,2,2,3,3,2,2,1,1,1,3,1};
        int k3= 11;
        System.out.println(countGood(nums3, k3));

        int[] nums = new int[]{1,1,1,1,1};
        int k = 10;
        System.out.println(countGood(nums, k));

        int[] nums2 = new int[]{3,1,4,3,2,2,4};
        int k2 = 2;
        System.out.println(countGood(nums2, k2));
    }

    public static long countGood(int[] nums, int k) {
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        int left = 0;
        long pairCount = 0;
        long answer = 0;

        for (int right = 0; right < n; right++) {
            int num = nums[right];
            int count = map.getOrDefault(num, 0);
            pairCount += count;
            map.put(num, count + 1);

            while (pairCount >= k) {
                answer += n - right;
                int leftValue = nums[left];
                int leftCount = map.get(leftValue);
                pairCount -= leftCount - 1;
                map.put(leftValue, leftCount - 1);
                left++;
            }
        }

        return answer;
    }
}
