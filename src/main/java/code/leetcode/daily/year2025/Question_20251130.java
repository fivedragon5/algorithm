package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/make-sum-divisible-by-p/description/?envType=daily-question&envId=2025-11-30
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *  1 <= p <= 10^9
 *
 * 문제)
 *  1. 양수배열 nums가 주어진다.
 *  2. 부분배열을 제거하여 나머지 원소들의 합이 p로 나누어 떨어지도록 한다.
 *  3. 제거하는 부분배열의 길이가 최소가 되도록 한다.
 *  4. 불가능한 경우 -1을 반환한다.
 *
 * 풀이)
 *  1. 전체 합을 p로 나눈 나머지를 구한다.
 *  2. 접두사 합을 이용하여 각 위치까지의 합을 p로 나눈 나머지를 계산한다.
 *  3. 해시맵을 사용하여 이전에 본 접두사 합의 나머지와 그 위치를 저장한다.
 *  4. 현재 접두사 합의 나머지에서 제거해야 할 부분배열의 나머지를 계산하고, 해시맵에서 해당 나머지가 있는지 확인한다.
 *  5. 최소 길이를 갱신하고, 최종적으로 최소 길이를 반환한다.
 *
 */

public class Question_20251130 {
    public static void main(String args[]) throws IOException {
        int[] nums = {3,1,4,2};
        int p = 6;
        System.out.println(minSubarray(nums, p));

        nums = new int[]{1, 2, 3};
        p = 3;
        System.out.println(minSubarray(nums, p));
    }

    public static int minSubarray(int[] nums, int p) {
        int sum = 0;
        for (int num : nums) {
            sum = (sum + num) % p;
        }
        if (sum == 0) return 0;

        HashMap<Integer, Integer> lastSeen = new HashMap<>();
        lastSeen.put(0, -1); // prefix[0] = 0

        int n = nums.length;
        int minLen = n;

        int prefix = 0;
        for (int i = 0 ; i < n; i++) {
            prefix = (prefix + nums[i]) % p;

            // 제거할 구간 찾기: prefix[i] - target = 이전 prefix[j]
            int needed = (prefix - sum + p) % p;

            if (lastSeen.containsKey(needed)) {
                minLen = Math.min(minLen, i - lastSeen.get(needed));
            }

            lastSeen.put(prefix, i);
        }

        return minLen == n ? -1 : minLen;
    }
}
