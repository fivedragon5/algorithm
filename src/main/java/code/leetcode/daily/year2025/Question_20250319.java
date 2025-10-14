package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/minimum-operations-to-make-binary-array-elements-equal-to-one-i/description/?envType=daily-question&envId=2025-03-19
 *
 * 제한)
 *  3 <= nums.length <= 10^5
 *  0 <= nums[i] <= 1
 *
 * 문제)
 *  1. 0,1 로 이루어진 nums 배열이 주어진다.
 *  2. 연속된 부분 배열 3개를 뒤집어서 모두 1로 만들 수 있는 최소수행 수를 반환하기
 *   - 뒤집기 : 0 -> 1, 1 -> 0
 *  3. 만약 모두1로 만들 수 없다면 -1 을 반환
 *
 * 풀이)
 *  1. nums 을 nums.length - 2 만큼 반복 하면서 원소가 0일경우 뒤집기(flipNums) 함수 실행
 *   - 1->0, 0->1
 *  2. 반복종료 후 nums의 마지막 2개의 원소중 0이 있을경우 -1 반환
 *   - 0이 없을 경우 : 뒤집기 함수를 실행한 횟수 반환
 *
 */

public class Question_20250319 {
    public static void main(String args[]) throws IOException {
        int[] nums3 = new int[]{0,1,1,1,0,0};
        System.out.println(minOperations(nums3)); // 3

        int[] nums = new int[]{0,1,1,1};
        System.out.println(minOperations(nums)); // -1
    }

    public static int minOperations(int[] nums) {
        int answer = 0;
        for (int i = 0; i <= nums.length - 3; i++) {
            if (nums[i] == 0) {
                flipNums(nums, i);
                answer++;
            }
        }
        if (nums[nums.length - 2] == 0 || nums[nums.length - 1] == 0) {
            return -1;
        }
        return answer;
    }

    private static void flipNums(int[] nums, int start) {
        for (int i = start; i < start + 3; i++) {
            nums[i] = nums[i] == 0 ? 1 : 0;
        }
    }
}
