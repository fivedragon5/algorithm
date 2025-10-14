package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/divide-array-into-equal-pairs/description/?envType=daily-question&envId=2025-03-17
 *
 * 제한)
 *  nums.length == 2 * n
 *  1 <= n <= 500
 *  1 <= nums[i] <= 500
 *
 * 문제)
 *  1. nums의 길이가 2 * n인 integer타입의 배열이 주어진다.
 *  2. nums을 n개의 쌍으로 만들 수 있으면 true, 없으면 false로 반환
 *   - 쌍으로 만들기 위한 조건 : 각 원소가 같을 경우 쌍으로 만들 수 있음
 *
 * 풀이)
 *  1. nums의 원소를 담을 Set 선언
 *  2. nums를 순회하면서 set에 num을 add
 *   - 값이 있을 경우 set에서 삭제
 *  3. set이 비어있을 경우 true, 값이 남아 있을 경우 false
 */

public class Question_20250317 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{3,2,3,2,2,2};
        System.out.println(divideArray(nums));

        int[] nums2 = new int[]{1,2,3,4};
        System.out.println(divideArray(nums2));
    }

    public static boolean divideArray(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num: nums) {
            if (!set.add(num)) {
                set.remove(num);
            }
        }
        return set.isEmpty();
    }
}
