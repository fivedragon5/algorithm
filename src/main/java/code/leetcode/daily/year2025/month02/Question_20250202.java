package code.leetcode.daily.year2025.month02;

import java.io.IOException;

/**
 * https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/description/?envType=daily-question&envId=2025-02-02
 *
 * 제한)
 *  1 <= nums.length <= 100
 *  1 <= nums[i] <= 100
 *
 * 문제)
 *  1. 배열 nums가 주어졌을 때, 오름차순 으로 정렬된 배열이었다가 일정 횟수만큼 회전된 배열이라면 true를 반환하세요.
 *      그렇지 않다면 false를 반환하세요.
 *  2. 배열에는 중복된 값이 포함될 수 있습니다.
 *  3. 배열 A를 x번 회전하면, 동일한 길이를 가진 배열 B가 생성되며, 이는 다음 조건을 만족합니다.
 *      - A[i] == B[(i + x) % A.length]
 *
 * 풀이)
 *  1. 주어진 nums 배열을 순회하면서 오름차순 정렬되어 있지 않은 Index를 찾기
 *      - 없다면 이미 오름차순으로 정렬된 배열이기에 True 반환
 *  2. 찾은 Index를 기준으로 주어진 nums배열의 마지막까지 오름차순 확인
 *      - 마지막까지 확인후 nums[nums.length - 1] > nums[0] 까지 확인
 */

public class Question_20250202 {
    public static void main(String args[]) throws IOException {

        int[] nums = {3,4,5,1,2};
        System.out.println(check(nums));

        int[] nums2 = {2,1,3,4};
        System.out.println(check(nums2));

        int[] nums3 = {1,2,3};
        System.out.println(check(nums3));
    }

    public static boolean check(int[] nums) {
        int rotateIndex = -1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                rotateIndex = i;
                break;
            }
        }
        if (rotateIndex == -1) {
            return true;
        }
        for (int i = rotateIndex + 1; i < nums.length; i++) {
            if (nums[i-1] > nums[i]) {
                return false;
            }
        }
        if (nums[nums.length - 1] > nums[0]) {
            return false;
        }
        return true;
    }
}
