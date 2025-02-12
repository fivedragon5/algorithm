package code.leetcode.daily;

import java.io.IOException;
import java.util.HashMap;

/**
 * https://leetcode.com/problems/max-sum-of-a-pair-with-equal-sum-of-digits/description/?envType=daily-question&envId=2025-02-12
 *
 * 제한)
 *  1 <= nums.length <= 10^5
 *  1 <= nums[i] <= 10^9
 *
 * 문제)
 *  1. 0부터 시작하는 양의 정수로 이루어진 배열 nums가 주어짐
 *  2. 서로다른 두 인덱스 i, j를 선택할 수 있으며, 이때 nums[i], nums[j]의 각 자리의 합이 동일
 *  3. 이런 조건을 만족하는 (i, j) 중 nums[i] + nums[j] 의 최대값을 구하시오
 *
 * 풀이)
 *  1. Map<Integer, Integer> 자리수의 합 : num 을 저장
 *   - 이때 num을 갱신해 주는 과정에서 가장 큰값으로 갱신
 *  2. nums을 순회하면서 Map에 값이 있을 경우 최대값 갱신
 */

public class Question_20250212 {
    public static void main(String args[]) throws IOException {
        int[] nums = {18,43,36,13,7};
        maximumSum(nums);

        nums = new int[]{10,12,19,14};
        maximumSum(nums);
    }

    public static int maximumSum(int[] nums) {
        HashMap<Integer, Integer> sumAndMaxNumber = new HashMap<>();
        int max = -1;
        for (int i = 0; i < nums.length; i++) {
            int currentNumber = nums[i];
            int currentDigitsSum = getDigitsSum(currentNumber);
            int digitMax = sumAndMaxNumber.getOrDefault(currentDigitsSum, 0);
            if (digitMax != 0) {
                max = Math.max(currentNumber + digitMax, max);
            }
            sumAndMaxNumber.put(currentDigitsSum, Math.max(digitMax, currentNumber));
        }
        System.out.println(max);
        return max;
    }

    private static int getDigitsSum(int num) {
        int result = 0;
        while (num >= 10) {
            result += num % 10;
            num /= 10;
        }
        return result + num;
    }
}
