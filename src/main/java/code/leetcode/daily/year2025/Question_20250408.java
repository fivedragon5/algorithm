package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/minimum-number-of-operations-to-make-elements-in-array-distinct/?envType=daily-question&envId=2025-04-08
 *
 * 제한)
 *  1 <= nums.length <= 100
 * 1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 정수배열 nums가 주어진다.
 *  2. 배열에 모든 원소가 모두 다르기위해 필요한 최소 연산 수 를 구하기
 *   - 연산 : 배열의 맨 앞에서 3개의 원소 삭제, 만약 남은 원소가 3개보다 작을 경우, 모든 원소 삭제
 *
 * 풀이)
 *  1. nums를 반복하면서 2번 이상 등장 하는 수 중에 가장 큰 Index를 저장
 *  2. 해당 Index 만큼 앞에서 배열을 삭제
 *
 */

public class Question_20250408 {
    public static void main(String args[]) throws IOException {
        int[] nums5 = new int[]{8,10,7,1,5,1,8};
        System.out.println(minimumOperations(nums5));

        int[] nums4 = new int[]{1,2,3,4,2,3,3,5,7};
        System.out.println(minimumOperations(nums4));

        int[] nums = new int[]{4,5,6,4,4};
        System.out.println(minimumOperations(nums));

        int[] nums2 = new int[]{6,7,8,9};
        System.out.println(minimumOperations(nums2));

    }

    public static int minimumOperations(int[] nums) {
        int answer = 0;
        int index = -1;
        HashMap<Integer, Integer> map = new HashMap<>(); // number, index
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int numIndex = map.getOrDefault(num, -1);
            map.put(num, i);
            if (numIndex != -1) {
                index = Math.max(numIndex, index);
            }
        }

        if (index == -1) {
            return answer;
        }

        return index / 3 + 1;
    }
}
