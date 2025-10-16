package code.leetcode.daily.year2025;

import java.io.IOException;

/**
 * https://leetcode.com/problems/smallest-missing-non-negative-integer-after-operations/description/?envType=daily-question&envId=2025-10-16
 *
 * 제한)
 * 1 <= nums.length, value <= 10^5
 * -10^9 <= nums[i] <= 10^9
 *
 * 문제)
 *  1. 정수 배열 nums, 정수 value 가 주어짐
 *  2. 한번의 연산에서, nums의 임의의 원소에 value를 더하거나 뺼 수 있다.
 *  3. 주어진 연산은 원하는 만큼 적용했을 때, 배열의 MEX를 최대로 만들 수 있는 값을 반환하기
 *   - MEX : 배열에 존재하지 않는 가장 작은 비음수가 아닌 정수(0이상)를 의미
 *   - ex) nums = [1,-10,7,13,6,8], value = 5
 *      - 0은 nums에 존재하지 않음 | -10 + 5 + 5 = 0 -> nums = [1,`0`,7,13,6,8]
 *      - 2는 nums에 존재하지 않음 | 7 - 5 = 2 -> nums = [1,0,`2`,13,6,8]
 *      - 3은 nums에 존재하지 않음 | 8 - 5 = 3 -> nums = [1,0,2,13,6,`3`]
 *      - 4는 nums에 존재하지 않음 | 어떤 원소로도 4를 만들 수 없음 -> MEX = 4
 *
 * 풀이)
 *  1. nums를 순회하면서 value로 나누었을 때 나머지 값이 index인 값의 개수를 count 배열에 저장
 *   - 음수 일 경우, value로 나누었을 때 음수가 나올 수 있으므로 양수로 변환
 *  2. index = 0부터 시작해서 count[index % value] > 0 인 동안 index 증가
 *   - count[index % value]-- : 해당 나머지 값이 index인 값
 *  3. count[index % value] 가 0이 되는 순간의 index 반환
 */

public class Question_20251016 {
    public static void main(String args[]) throws IOException {
        int[] nums = new int[]{1,-10,7,13,6,8};
        int value = 5;
        System.out.println(findSmallestInteger(nums, value)); // 4

        int[] nums2 = new int[]{1,-10,7,13,6,8};
        value = 7;
        System.out.println(findSmallestInteger(nums, value)); // 2
    }

    private static int findSmallestInteger(int[] nums, int value) {
        int n = nums.length;
        // value로 나누었을 때 나머지 값이 index인 값의 개수
        int[] count = new int[value];

        for (int i = 0; i < n; i ++) {
            int num = nums[i];
            if (num < 0) {
                // 음수 일 경우, value로 나누었을 때 음수가 나올 수 있으므로 양수로 변환
                num = (num % value + value) % value;
            }
            count[num % value]++;
        }

        int index = 0;

        while (count[index % value] > 0) {
            count[index % value]--;
            index++;
        }

        return index;
    }
}
