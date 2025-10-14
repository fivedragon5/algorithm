package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/maximum-count-of-positive-integer-and-negative-integer/description/?envType=daily-question&envId=2025-03-12
 *
 * 제한)
 *  1 <= nums.length <= 2000
 *  -2000 <= nums[i] <= 2000
 *  nums is sorted in a non-decreasing order.
 *
 * 문제)
 *  1. 오름차순으로 정렬된 nums 배열이 주어진다.
 *  2. nums에서 양의정수, 음의정수 각각의 갯수 중 큰 더 큰 갯수를 반환하기
 *  3. 단 '0'은 양,음 의정수 어디에도 속하지 않는다
 *
 * 풀이)
 *  1. maximumCount() // 이분 탐색 이용
 *   - 이분탐색을 사용해서 음수가 끝나는 부분을 탐색
 *   - 이분탐색을 사용해서 양수가 시작되는 부부을 탐색
 *   - 이분탐색을 두번해서 더 큰 값을 반환
 *
 *  2. maximumCount2() // 구현? 에 가까운 풀이
 *   - nums 반복하면서 음, 양의 정수 시작부분을 구함
 *   - 초기값은 -1
 *   - 경우의 수에따라 계산법을 다르게 해서 반환
 */

public class Question_20250312 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{-2,-1,-1,1,2,3};
        System.out.println(maximumCount(nums)); // 3

        int[] nums2 = new int[]{-3,-2,-1,0,0,1,2};
        System.out.println(maximumCount(nums2)); // 3

        int[] nums3 = new int[]{5,20,66,1314};
        System.out.println(maximumCount(nums3)); // 4

        int[] nums4 = new int[]{0, 0};
        System.out.println(maximumCount(nums4)); // 0

        int[] nums5 = new int[]{-2, -1};
        System.out.println(maximumCount(nums5)); // 2

        int[] nums6 = new int[]{-2, -1, 0};
        System.out.println(maximumCount(nums6)); // 2

        int[] nums7 = new int[]{0, 1, 2};
        System.out.println(maximumCount(nums7)); // 2
    }

    public static int maximumCount(int[] nums) {
        int negCount = binarySearch(nums, 0); // 0의 위치
        int posCount = nums.length - binarySearch(nums, 1); // 양의 정수 시작 위치
        return Math.max(negCount, posCount);
    }

    private static int binarySearch(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int result = nums.length;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                result = mid;
                right = mid - 1;
            }
        }
        return result;
    }

    public static int maximumCount2(int[] nums) {
        return findPositiveIndex(nums);
    }

    private static int findPositiveIndex(int[] nums) {
        int left = -1;
        int right = -1;
        for (int i = 0; i < nums.length; i++) {
            if (left == -1) {
                if (nums[i] >= 0) {
                    left = i;
                }
            }

            if (nums[i] > 0) {
                right = i;
                break;
            }
        }

        if (right == 0 || (left == -1 && right == -1)) {
            return nums.length;
        } else if (left != -1 && right != -1) {
            return Math.max(left, nums.length - right);
        } else {
            return left;
        }
    }
}
