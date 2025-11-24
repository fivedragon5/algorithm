package code.leetcode.daily.year2025;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/binary-prefix-divisible-by-5/description/?envType=daily-question&envId=2025-11-24
 *
 * 제한)
 * 1 <= nums.length <= 10^5
 * nums[i] is either 0 or 1.
 *
 * 문제)
 * 1. 이진 배열 nums(0부터 인덱스)가 주어진다.
 * 2. xi를 부분 배열 nums[0..i]의 이진 표현인 숫자로 정의한다. (가장 중요한 비트에서 가장 덜 중요한 비트까지)
 *  ex) nums = [1,0,1]인 경우, x0 = 1, x1 = 2, x2 = 5이다.
 * 3. 불리언 배열 answer를 반환하는데, answer[i]는 xi가 5로 나누어 떨어지면 true이다.
 *
 * 풀이)
 *  1. 이진수를 10진수로 변환하는 과정에서 매번 5로 나눈 나머지를 계산하여 메모리 사용을 줄인다.
 *  2. 각 단계에서의 나머지가 0인지 확인하여 결과 리스트 반환
 */

public class Question_20251124 {
    public static void main(String args[]) throws IOException {
        int[] nums = {0,1,1};
        System.out.println(prefixesDivBy5(nums));

        nums = new int[]{0,1,1,1,1,1};
        System.out.println(prefixesDivBy5(nums));
    }

    public static List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> result = new ArrayList<>();
        int val = 0;
        for (int num : nums) {
            val = ((val << 1) + num) % 5;
            result.add(val == 0);
        }
        return result;
    }
}
