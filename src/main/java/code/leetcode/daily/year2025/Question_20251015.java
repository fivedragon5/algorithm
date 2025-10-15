package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/adjacent-increasing-subarrays-detection-ii/description/?envType=daily-question&envId=2025-10-15
 * 제한)
 *  2 <= nums.length <= 2 * 105
 * -10^9 <= nums[i] <= 10^9
 *
 * 문제)
 *  1. 정수배열 nums 가 주어짐
 *  2. 인접한 2개의 부분 배열이 엄격히 증가하는 부분 배열의 최대 개수를 반환하기
 *   ex) nums = [2,5,7,8,9,2,3,4,3,1] -> [7,8,9][2,3,4] max(subarrays.length) = 3
 *  3. 단 부분 배열의 길이는 이상이다.
 *
 * 풀이)
 *  1. nums를 순회하면서 증가하는 부분 배열의 길이를 incLengths 에 저장
 *   - incLengths / 2 = 각 부분 배열의 최대 길이 가 될 수 있음
 *  2. incLengths 를 순회하면서 인접한 2개의 부분 배열
 *   - maxK = Math.max(maxK, Math.min(left, right))
 *  3. maxK 반환
 */

public class Question_20251015 {
    public static void main(String args[]) throws IOException {
//        List<Integer> nums = List.of(2,5,7,8,9,2,3,4,3,1);
        List<Integer> nums = List.of(-15,-13,4,7);
        System.out.println(maxIncreasingSubarrays(nums)); // 3
    }

    private static int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        List<Integer> incLengths = new ArrayList<>();
        int length = 1;
        int maxK = 1;

        for (int i = 1; i < n; i++) {
            if (nums.get(i) > nums.get(i - 1)) {
                length++;
            } else {
                maxK = Math.max(maxK, length / 2);
                incLengths.add(length);
                length = 1;
            }
        }
        maxK = Math.max(maxK, length / 2);
        incLengths.add(length);

        for (int i = 0; i < incLengths.size() - 1; i++) {
            int left = incLengths.get(i);
            int right = incLengths.get(i + 1);
            maxK = Math.max(maxK, Math.min(left, right));
        }

        return maxK;
    }
}
